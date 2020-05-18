package com.miaomiao.wedding.demo.dao;

import com.miaomiao.wedding.demo.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    @Insert("insert into order(order_manname,order_womanname,order_adress,order_date,order_area,order_city,order_phone,order_wechat,user_id,order_desc) values (#{orderManname},#{orderWomanname},#{orderAdress},#{orderDate},#{orderArea},#{orderCity},#{orderPhone},#{orderWechat},#{userId},#{orderDesc})")
    public Integer insertOrder(Order order);
}
