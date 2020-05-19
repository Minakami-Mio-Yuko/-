package com.miaomiao.wedding.demo.dao;

import com.miaomiao.wedding.demo.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("insert into orders(order_manname,order_womanname,order_dress,order_adress,order_date,order_area,order_city,order_phone,order_wechat,user_id,order_desc,order_status,order_cameraman) values (#{orderManname},#{orderWomanname},#{orderAdress},#{orderDate},#{orderArea},#{orderCity},#{orderPhone},#{orderWechat},#{userId},#{orderDesc},#{orderStatus},#{orderCameraman})")
    public Integer insertOrder(Order order);

    @Select("select order_manname,order_womanname,order_adress,order_dress,order_date,order_area,order_city,order_phone,order_wechat,order_desc,order_status,order_cameraman from orders where user_id=#{_parameter}")
    public List<Order> findUserorder(Integer userid);

    @Select("select * from orders")
    public List<Order>findAllorder();


}
