package com.miaomiao.wedding.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.miaomiao.wedding.demo.entity.*;
import com.miaomiao.wedding.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    //返回未完成订单页面，iframe页面
    @RequestMapping("/undoneorder")
    public String undoneorder(){
        return "ManageOrder/undoneorder";
    }
    //返回完成订单页面，iframe页面
    @RequestMapping("/doneorder")
    public String doneorder(){
        return "ManageOrder/doneorder";
    }
    //返回全部摄影师页面，iframe页面
    @RequestMapping("/allcameraman")
    public String allcameraman(){
        return "ManageCameraman/allcameraman";
    }
    @RequestMapping("/sendcameraman")
    public String sendcameraman(){return "ManageCameraman/sendcameraman";}
    @RequestMapping("/unsendcameraman")
    public String unsendcameraman(){return "ManageCameraman/unsendcameraman";}
    @RequestMapping("/managepicture")
    public String managepicture(){return "ManagePicture/managepicture";}


    //返回全部订单表格
    @RequestMapping("/findallorder")
    @ResponseBody
    public JsonVo findallorder(){
        List list=userService.findAllorder();
        JsonVo<List<Order>> jsonVo=new JsonVo(0,"",list);
        jsonVo.setCount(1000);
        return jsonVo;
    }

    //返回已完成订单表格
    @RequestMapping("/finddoneorder")
    @ResponseBody
    public JsonVo findoneorder(){
        List list=userService.findDoneorder();
        JsonVo<List<Order>> jsonVo=new JsonVo(0,"",list);
        jsonVo.setCount(1000);
        return jsonVo;
    }

    //返回未完成订单表格
    @RequestMapping("/findundoneorder")
    @ResponseBody
    public JsonVo findundoneorder(){
        List list=userService.findUndoneorder();
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
    public Map editorder(@RequestBody String data){
        JSONObject jsonObject=JSONObject.parseObject(data);
        Order order=JSONObject.toJavaObject(jsonObject,Order.class);
        String status=order.getOrderStatus();
        Map map=new HashMap();
        if(status==null||status.equals("")){
            //第一步：先根据订单id返回之前摄影师的名字
            //第二步：根据该名字修改摄影师表对应摄影师状态（改为空闲）
            //第三步：修改订单摄影师名字以及其他项
            //第四步：修改摄影师表对应摄影师状态（改为工作）
            order.setOrderStatus("未完成");
            Integer i=userService.editorderA(order);
            map.put("code",i.toString());

        }else{
            order.setOrderCameraman("未指派");
            //第一步：先根据订单id返回之前摄影师的名字
            //第二步：根据该名字修改摄影师表对应摄影师状态（改为空闲）
            //第三步：直接将订单摄影师名字改为未指派
            Integer i=userService.editorderB(order);
            map.put("code",i.toString());
        }
        return map;
    }

    //删除订单
    @RequestMapping("/deleteorder")
    @ResponseBody
    public Map deleteorder(Integer orderId){
        //第一步：先根据订单id返回之前摄影师的名字
        //第二步：根据该名字修改摄影师表对应摄影师状态（改为空闲）
        //第三步：删除订单
        Map map=new HashMap();
        Integer i=userService.deleteOrder(orderId);
        map.put("code",i.toString());
        return map;
    }

    //返回所有摄影师表格
    @RequestMapping("/findallcamerman")
    @ResponseBody
    public JsonVo findAllcamerman(){
        List list=userService.allCameraman();
        JsonVo<List<Order>> jsonVo=new JsonVo(0,"",list);
        jsonVo.setCount(1000);
        return jsonVo;
    }

    //新增摄影师执行
    @RequestMapping("/insertcameraman")
    @ResponseBody
    public Map insertcameraman(CameraMan cameraMan){
        Integer i=userService.insertcameraman(cameraMan);
        Map map=new HashMap();
        map.put("code",i);
        return map;
    }

    //返回已指派摄影师表格
    @RequestMapping("/findsendcamerman")
    @ResponseBody
    public JsonVo findSendcamerman(){
        List list=userService.sendcameraman();
        JsonVo<List<Order>> jsonVo=new JsonVo(0,"",list);
        jsonVo.setCount(1000);
        return jsonVo;
    }

    //返回未指派摄影师表格
    @RequestMapping("/findunsendcamerman")
    @ResponseBody
    public JsonVo findUnsendcamerman(){
        List list=userService.unsendcameraman();
        JsonVo<List<Order>> jsonVo=new JsonVo(0,"",list);
        jsonVo.setCount(1000);
        return jsonVo;
    }

    //删除摄影师执行
    @RequestMapping("/deletecameraman")
    @ResponseBody
    public Map deletecameraman(Integer id){
        Integer i=userService.deletecameraman(id);
        Map map=new HashMap();
        map.put("code",i);
        return map;
    }

    //修改摄影师执行
    @RequestMapping("/editcameraman")
    @ResponseBody
    public Map editcameraman(CameraMan cameraMan){
        Integer i=userService.editcameraman(cameraMan);
        Map map=new HashMap();
        map.put("code",i);
        return map;
    }

    //返回所有图片表格
    @RequestMapping("/manageallpicture")
    @ResponseBody
    public JsonVo manageallpicture(){
        List list=userService.manageallpicture();
        JsonVo<List<Order>> jsonVo=new JsonVo(0,"",list);
        jsonVo.setCount(1000);
        return jsonVo;
    }


    //上传客照
    @RequestMapping("/UploadPic")
    @ResponseBody
    public Map<String,String> uploadpic(@RequestParam("file") MultipartFile file){
        Map<String,String> map=new HashMap<>();
        String filename=file.getOriginalFilename();
        String image_path="F:\\喵喵婚纱\\用户上传图片\\"+filename;//真实路径
        File imagefile=new File(image_path);
        try {
            file.transferTo(imagefile);
            map.put("code","0");
        } catch (IOException e) {
            map.put("code","1");
        }
        return map;
    }

    @RequestMapping("/insertpic")
    @ResponseBody
    public Map insertpic(Picture pic){
        String src="/pic/image/"+pic.getPictureSrc();
        pic.setPictureSrc(src);
        Map map=new HashMap();
        Integer i=userService.insertpic(pic);
        map.put("code",i);
        return map;
    }


    //修改图片执行
    @RequestMapping("/editpicture")
    @ResponseBody
    public Map editpicture(Integer pictureId,String pictureName){
        Integer i=userService.editPic(pictureName,pictureId);
        Map map=new HashMap();
        map.put("code",i);
        return map;
    }

    //删除图片执行
    @RequestMapping("/deletepicture")
    @ResponseBody
    public Map deletepicture(Integer Id){
        Integer i=userService.deletePic(Id);
        Map map=new HashMap();
        map.put("code",i);
        return map;
    }





}
