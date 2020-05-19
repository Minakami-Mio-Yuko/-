package com.miaomiao.wedding.demo.controller;

import com.miaomiao.wedding.demo.entity.JsonVo;
import com.miaomiao.wedding.demo.entity.Order;
import com.miaomiao.wedding.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Managecontroller {

    @Autowired
    UserService userService;

    //返回全部订单页面
    @RequestMapping("/allorder")
    public String allorder(){
        return "ManageOrder/allorder";
    }

    //返回全部订单,iframe
    @RequestMapping("findallorder")
    @ResponseBody
    public JsonVo findallorder(){
        List list=userService.findAllorder();
        JsonVo<List<Order>> jsonVo=new JsonVo(0,"",list);
        jsonVo.setCount(1000);
        return jsonVo;
    }


}
