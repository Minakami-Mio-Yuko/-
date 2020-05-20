package com.miaomiao.wedding.demo.controller;

import com.miaomiao.wedding.demo.entity.JsonVo;
import com.miaomiao.wedding.demo.entity.Order;
import com.miaomiao.wedding.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Managecontroller {

    @Autowired
    UserService userService;

    //返回全部订单页面，iframe页面
    @RequestMapping("/allorder")
    public String allorder(){
        return "ManageOrder/allorder";
    }

    //返回全部订单表格
    @RequestMapping("findallorder")
    @ResponseBody
    public JsonVo findallorder(){
        List list=userService.findAllorder();
        JsonVo<List<Order>> jsonVo=new JsonVo(0,"",list);
        jsonVo.setCount(1000);
        return jsonVo;
    }

    //返回空闲摄影师列表
    @RequestMapping("/freecameraman")
    @ResponseBody
    public Map freeCameraman(){
        Map<String,List> map=new HashMap<>();
        List list=userService.freeCameraman();
        map.put("cameralist",list);
        return map;
    }

    //接收修改后的订单
    @RequestMapping("/editorder")
    @ResponseBody
    public Map editorder(Order order){
        String status=order.getOrderStatus();
        Map map=new HashMap();
        if(status.equals("未完成")){
            //第一步：先根据订单id返回之前摄影师的名字
            //第二步：根据该名字修改摄影师表对应摄影师状态（改为空闲）
            //第三步：修改订单摄影师名字以及其他项
            //第四步：修改摄影师表对应摄影师状态（改为工作）

        }else{
            //第一步：先根据订单id返回之前摄影师的名字
            //第二步：根据该名字修改摄影师表对应摄影师状态（改为空闲）
            //第三步：直接将订单摄影师名字改为未指派

        }
        return map;
    }


}
