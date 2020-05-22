package com.miaomiao.wedding.demo.dao;

import com.miaomiao.wedding.demo.entity.Picture;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PictureMapper {

    @Select("select * from picture limit #{currindex} , #{pagesize}")
    public List<Picture> findallpicture(@Param("currindex")Integer currindex,@Param("pagesize")Integer pagesize);

    @Select(" select count(*) from picture")
    @ResultType(Integer.class)
    public Integer count();

    @Select("select * from picture")
    public List<Picture> manageallpicture();

    @Insert("insert into picture(picture_name,picture_src) values (#{pictureName},#{pictureSrc})")
    public Integer insertpic(Picture pic);
}
