package com.miaomiao.wedding.demo.dao;

import com.miaomiao.wedding.demo.entity.CameraMan;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CameramanMapper {

    @Select("select * from cameraman")
    public List<CameraMan> allcameraman();

    @Select("select * from cameraman where cameraman_status='工作'")
    public List<CameraMan> sendcameraman();

    @Select("select * from cameraman where cameraman_status='空闲' or cameraman_status='休假'" )
    public List<CameraMan> unsendcameraman();

    @Update("update cameraman set cameraman_name=#{cameramanName},cameraman_status=#{cameramanStatus} where cameraman_id=#{cameramanId}")
    public Integer editcameraman(CameraMan cameraMan);

    @Delete("delete from cameraman where cameraman_id=#{cameramanId}")
    public Integer deletecameraman(Integer id);

    @Insert("insert into cameraman(cameraman_name,cameraman_status) values (#{cameramanName},#{cameramanStatus})")
    public Integer insertcameraman(CameraMan cameraMan);
}
