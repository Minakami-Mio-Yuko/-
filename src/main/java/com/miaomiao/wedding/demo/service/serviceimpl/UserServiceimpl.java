package com.miaomiao.wedding.demo.service.serviceimpl;

import com.miaomiao.wedding.demo.dao.UserMapper;
import com.miaomiao.wedding.demo.entity.Group;
import com.miaomiao.wedding.demo.entity.JsonVo;
import com.miaomiao.wedding.demo.entity.User;
import com.miaomiao.wedding.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceimpl implements UserService {

    @Autowired
    UserMapper userMapper;

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
}