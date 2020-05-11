package com.miaomiao.wedding.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.miaomiao.wedding.demo.entity.GetMsg;
import com.miaomiao.wedding.demo.entity.SendMsg;
import com.miaomiao.wedding.demo.entity.User;
import com.miaomiao.wedding.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket/{userid}")
@Component
public class Websocket {
    //用来存放每个客户端对应的Mywebsocket对象，每创建一次连接就会新增一个bean
    private static Hashtable<Integer,Websocket> webSocketTable=new Hashtable<>();//静态

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private Integer userid;
    private User user=new User();


    private static UserService service;
    @Autowired
    public void setUserservice(UserService service){//websocket是多对象模式，与spring默认的单例模式自动装配冲突，所以要设为静态
        Websocket.service=service;
    }


    //连接成功调用
    @OnOpen
    public void onOpen(Session session,@PathParam("userid")Integer userid){
        this.session=session;
        this.userid=userid;
        webSocketTable.put(userid,this);
        System.out.println("有新连接加入！当前人数为"+webSocketTable.size());
        user.setId(userid);
        user.setStatus("online");//设置为在线
        service.updatestatus(user);
        for(Iterator<Integer>iterator=webSocketTable.keySet().iterator();iterator.hasNext();){//通知剩余在线成员有人上线
            int key=iterator.next();
            webSocketTable.get(key).session.getAsyncRemote().sendText("online"+userid);
        }
    }

    //连接关闭的方法
    @OnClose
    public void onClose(){
        webSocketTable.remove(userid);
        System.out.println("有一连接关闭！当前在线人数为"+webSocketTable.size());
        user.setStatus("offline");//设置为下线
        service.updatestatus(user);
        for(Iterator<Integer>iterator=webSocketTable.keySet().iterator();iterator.hasNext();){//通知剩余在线成员有人下线
            int key=iterator.next();
            webSocketTable.get(key).session.getAsyncRemote().sendText("offline"+userid);
        }
    }

    //收到客户端信息后调用的方法
    @OnMessage
    public void onMessage(String data){
        JSONObject jsonObject = JSONObject.parseObject(data);//将json字符串转换为jsonobject
        SendMsg msg=(SendMsg) JSONObject.toJavaObject(jsonObject,SendMsg.class);//将jsonobject转换为实体类
        if(msg.getTo().getType().equals("friend")){
            sendtouser(msg);
        }
        if(msg.getTo().getType().equals("group")){
            sendtogroup(msg);
        }

    }

    //发生错误时调用
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }



    //私发
    public void sendtouser(SendMsg msg){
        Integer toid=msg.getTo().getId();
        GetMsg toMsg=new GetMsg();
        toMsg.setId(msg.getMine().getId());
        toMsg.setAvatar(msg.getMine().getAvatar());
        toMsg.setContent(msg.getMine().getContent());
        toMsg.setFromid(msg.getMine().getId());
        toMsg.setType(msg.getTo().getType());
        toMsg.setUsername(msg.getMine().getUsername());
        toMsg.setMine(false);
        toMsg.setTimestamp(System.currentTimeMillis());
        try {
            Websocket tofriend=webSocketTable.get(toid);
            tofriend.session.getBasicRemote().sendText(JSONObject.toJSONString(toMsg));
        } catch (NullPointerException e) {
            webSocketTable.get(userid).session.getAsyncRemote().sendText("0");//如果捕捉到空指针异常就发送用户离线信息
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //群发
    public void sendtogroup(SendMsg msg){
        GetMsg toMsg=new GetMsg();
        int sendid=msg.getMine().getId();
        toMsg.setId(1);
        toMsg.setAvatar(msg.getMine().getAvatar());
        toMsg.setContent(msg.getMine().getContent());
        toMsg.setFromid(msg.getMine().getId());
        toMsg.setType(msg.getTo().getType());
        toMsg.setUsername(msg.getMine().getUsername());
        toMsg.setMine(false);
        toMsg.setTimestamp(System.currentTimeMillis());
        for(Iterator<Integer>iterator=webSocketTable.keySet().iterator();iterator.hasNext();){
            int key=iterator.next();
            if(key!=sendid) {
                webSocketTable.get(key).session.getAsyncRemote().sendText(JSONObject.toJSONString(toMsg));
            }
        }
    }

}
