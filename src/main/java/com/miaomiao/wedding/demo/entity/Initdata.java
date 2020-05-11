package com.miaomiao.wedding.demo.entity;

import java.util.List;

public class Initdata {
    User mine;
    List friend;
    List<Group>group;

    public User getMine() {
        return mine;
    }

    public void setMine(User mine) {
        this.mine = mine;
    }

    public List getFriend() {
        return friend;
    }

    public void setFriend(List friend) {
        this.friend = friend;
    }

    public List<Group> getGroup() {
        return group;
    }

    public void setGroup(List<Group> group) {
        this.group = group;
    }

    public Initdata(User mine,List friend,List group){
        this.mine=mine;
        this.friend=friend;
        this.group=group;
    }

    public Initdata(){};
}
