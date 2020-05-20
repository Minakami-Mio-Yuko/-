package com.miaomiao.wedding.demo.service;

import com.miaomiao.wedding.demo.entity.Group;
import com.miaomiao.wedding.demo.entity.JsonVo;
import com.miaomiao.wedding.demo.entity.Order;
import com.miaomiao.wedding.demo.entity.User;

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
}
