package com.miaomiao.wedding.demo.service;

import com.miaomiao.wedding.demo.entity.*;

import java.util.List;

public interface UserService {
    JsonVo init(Integer id);
    User mine(Integer id);
    List<User> findfriend(Integer id);
    List<Group> findgroup();
    List<User> findalluser();
    public void updatestatus(User para);
    User finduserByLogin(User user);
    Integer insertUser(User user);
    Integer insertFriend(Integer userid,Integer friendid);
    Integer insertOrder(Order order);
    List<Order> findUserOrder(Integer userid);
    List<Order> findAllorder();
    public List<String>freeCameraman();
    public Integer editorderA(Order order);
    public Integer editorderB(Order order);
    public List<Order>findDoneorder();
    public List<Order>findUndoneorder();
    public Integer deleteOrder(Integer orderId);
    public List<CameraMan> allCameraman();
    public Integer insertcameraman(CameraMan cameraMan);
    public List<CameraMan> sendcameraman();
    public List<CameraMan> unsendcameraman();
    public Integer editcameraman(CameraMan cameraMan);
    public Integer deletecameraman(Integer id);
    public List<Picture> findAllpicture(Integer currpage,int pagesize);
}
