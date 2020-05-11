package com.miaomiao.wedding.demo.entity;

import org.apache.catalina.User;

import java.util.List;

public class Friends {
    String groupname;
    Integer id;
    List<User>list;

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public Friends(String groupname,Integer id,List list){
        this.groupname=groupname;
        this.id=id;
        this.list=list;
    }

    public Friends(){}
}
