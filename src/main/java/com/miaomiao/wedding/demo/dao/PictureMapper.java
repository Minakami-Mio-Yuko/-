package com.miaomiao.wedding.demo.dao;

import com.miaomiao.wedding.demo.entity.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PictureMapper {

    @Select("select * from picture limit #{currindex} , #{pagesize}")
    public List<Picture> findallpicture(@Param("currindex")Integer currindex,@Param("pagesize")Integer pagesize);
}
