package com.miaomiao.wedding.demo.dao;

import com.miaomiao.wedding.demo.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("insert into orders(order_manname,order_womanname,order_dress,order_adress,order_date,order_area,order_city,order_phone,order_wechat,user_id,order_desc,order_status,order_cameraman) values (#{orderManname},#{orderWomanname},#{orderDress},#{orderAdress},#{orderDate},#{orderArea},#{orderCity},#{orderPhone},#{orderWechat},#{userId},#{orderDesc},#{orderStatus},#{orderCameraman})")
    public Integer insertOrder(Order order);

    @Select("select order_manname,order_womanname,order_adress,order_dress,order_date,order_area,order_city,order_phone,order_wechat,order_desc,order_status,order_cameraman from orders where user_id=#{_parameter}")
    public List<Order> findUserorder(Integer userid);

    @Select("select * from orders")
    public List<Order>findAllorder();

    @Select("select * from orders where order_status='完成'")
    public List<Order>findDoneorder();

    @Select("select * from orders where order_status='未完成'")
    public List<Order>findUndoneorder();

    @Select("select cameraman_name from cameraman where cameraman_status='空闲'")
    public List<String>freeCameraman();

    @Select("select order_cameraman from orders where order_id=#{_parameter}")
    public String cameraman(Integer orderid);//第一步

    @Update("update cameraman set cameraman_status='空闲' where cameraman_name=#{_parameter}")
    public Integer freecamera(String name);//第二步

    @Update("update orders set order_manname=#{orderManname},order_womanname=#{orderWomanname},order_adress=#{orderAdress},order_date=#{orderDate},order_area=#{orderArea},order_city=#{orderCity},order_phone=#{orderPhone},order_wechat=#{orderWechat},order_cameraman=#{orderCameraman},order_desc=#{orderDesc},order_status=#{orderStatus},order_dress=#{orderDress} where order_id=#{orderId}")
    public Integer updateorder(Order order);//第三步

    @Update("update cameraman set cameraman_status='工作' where cameraman_name=#{_parameter}")
    public Integer workcamera(String name);//第四步

    @Delete("delete from orders where order_id=#{_parameter}")
    public Integer deleteorder(Integer orderId);


}
