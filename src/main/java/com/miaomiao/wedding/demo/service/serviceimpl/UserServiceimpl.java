package com.miaomiao.wedding.demo.service.serviceimpl;

import com.miaomiao.wedding.demo.dao.OrderMapper;
import com.miaomiao.wedding.demo.dao.UserMapper;
import com.miaomiao.wedding.demo.entity.Group;
import com.miaomiao.wedding.demo.entity.JsonVo;
import com.miaomiao.wedding.demo.entity.Order;
import com.miaomiao.wedding.demo.entity.User;
import com.miaomiao.wedding.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceimpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public JsonVo init(Integer id) {
        return null;
    }

    @Override
    public User mine(Integer id) {
        User mine=userMapper.finduserById(id);
        return mine;
    }

    @Override
    public List<User> findfriend(Integer id) {
        List<User> list=userMapper.findfriend(id);
        return list;
    }

    @Override
    public List<Group> findgroup() {
        List<Group> group=userMapper.findgroup();
        return group;
    }

    @Override
    public List<User> findalluser() {
        List<User> users=userMapper.findalluser();
        return users;
    }

    @Override
    public void updatestatus(User para) {
        userMapper.updatestatus(para);
    }

    @Override
    public User finduserByLogin(User user) {
       return userMapper.finduserByLogin(user);
    }

    @Override
    public Integer insertUser(User user) {
        userMapper.insertUser(user);
        return user.getId();
    }

    @Override
    public Integer insertFriend(Integer userid, Integer friendid) {
        return userMapper.insertFriend(userid,friendid);
    }

    @Override
    public Integer insertOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    @Override
    public List<Order> findUserOrder(Integer userid) {
        List list=orderMapper.findUserorder(userid);
        return list;
    }
}
