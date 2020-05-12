package com.miaomiao.wedding.demo.dao;


import com.miaomiao.wedding.demo.entity.Group;
import com.miaomiao.wedding.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    //通过id查找用户
    public User finduserById(Integer id);


    //查找所有用户
    public List<User> findalluser();

    //通过id查找该用户的朋友
    public List<User> findfriend(Integer id);

    //通过用户名和密码查找用户
    public User finduserByLogin(User user);


    //查找群组
    @Select("select * from groupss")
    @Results({
            @Result(id = true,column = "group_id",property = "id"),
            @Result(column="group_name",property="groupname"),
            @Result(column="group_avatar",property="avatar"),

    })
    public List<Group> findgroup();

    //更新用户状态
    public void updatestatus(User para);

    //插入用户
    public Integer insertUser(User user);

    //插入好友
    public Integer insertFriend(@Param("userid") Integer userid,@Param("friendid") Integer friendid);




}
