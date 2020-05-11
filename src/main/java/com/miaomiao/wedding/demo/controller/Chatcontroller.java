package com.miaomiao.wedding.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.miaomiao.wedding.demo.entity.*;
import com.miaomiao.wedding.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Chatcontroller {

    @Autowired
    UserService userService;


    @RequestMapping("/login")
    public String login(){
        return "login";
    }



    @RequestMapping("/chat")//返回用户主页
    public String chat(HttpSession session,@RequestBody String data){
        JSONObject jsonObject=JSONObject.parseObject(data);
        User user=JSONObject.toJavaObject(jsonObject,User.class);
        user=userService.finduserByLogin(user);
        Integer userid=user.getId();
        session.setAttribute("userid",userid);
        return "layimchat";
    }


    @RequestMapping("/init")//初始化聊天面板
    @ResponseBody
    public JsonVo init(Integer id){
        User mine=userService.mine(id);
        List<User> friend=userService.findfriend(id);
        List friendlist=new ArrayList();
        if(mine.getRole()==1) {
            Friends friends = new Friends("客户", 1, friend);//分组为客户的好友
            friendlist.add(friends);
        }else{
            Friends friends = new Friends("客服", 1, friend);//分组为客服的好友
            friendlist.add(friends);
        }
        List<Group> group=userService.findgroup();
        Initdata initdata=new Initdata(mine,friendlist,group);
        JsonVo jsonVo=new JsonVo(0,"",initdata);
        return jsonVo;
    }

    @RequestMapping("/members")//获取群员信息
    @ResponseBody
    public JsonVo members(){
        List<User> users=userService.findalluser();
        Map<String,List<User>> map=new HashMap<>();
        map.put("list",users);
        JsonVo jsonVo=new JsonVo(0,"",map);
        return jsonVo;
    }

    @RequestMapping("/uploadimage")//聊天过程上传图片
    @ResponseBody
    public JsonVo uploadumage(MultipartFile file){
        Map<String,String> map=new HashMap<>();
        String filename=file.getOriginalFilename();
        String image_path="F:\\喵喵婚纱\\用户上传图片\\"+filename;//真实路径
        String iamge_src="/pic/image/"+filename;//虚拟路径
        File imagefile=new File(image_path);
        try {
            file.transferTo(imagefile);
            map.put("src",iamge_src);
        } catch (IOException e) {
            JsonVo jsonVo=new JsonVo(1,"上传失败",null);
            return jsonVo;
        }
        JsonVo jsonVo=new JsonVo(0,"",map);
        return jsonVo;
    }

    @RequestMapping("/uploadfile")//聊天过程上传文件
    @ResponseBody
    public JsonVo uploadfile(MultipartFile file){
        Map<String,String> map=new HashMap<>();
        String filename=file.getOriginalFilename();
        String file_path="F:\\喵喵婚纱\\用户上传文件\\"+filename;//真实路径
        String file_src="/file/"+filename;//虚拟路径
        File userfile=new File(file_path);
        try {
            file.transferTo(userfile);
            map.put("src",file_src);
        } catch (IOException e) {
            JsonVo jsonVo=new JsonVo(1,"上传失败",null);
            return jsonVo;
        }
        JsonVo jsonVo=new JsonVo(0,"",map);
        return jsonVo;
    }


    @RequestMapping("/testmine")
    @ResponseBody
    public User test(Integer id){
        return userService.mine(id);
    }

    @RequestMapping("/testfriend")
    @ResponseBody
    public List<User> testfriend(Integer id){
        return userService.findfriend(id);
    }

    @RequestMapping("/testgroup")
    @ResponseBody
    public List<Group> findgroup(){
        return userService.findgroup();
    }


}