package com.miaomiao.wedding.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Myspringmvc implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/pic/head/**").addResourceLocations("file:/F:/喵喵婚纱/头像/");
        registry.addResourceHandler("/pic/image/**").addResourceLocations("file:/F:/喵喵婚纱/用户上传图片/");
        registry.addResourceHandler("/file/**").addResourceLocations("file:/F:/喵喵婚纱/用户上传文件/");
    }
}
