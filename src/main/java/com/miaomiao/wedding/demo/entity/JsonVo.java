package com.miaomiao.wedding.demo.entity;

public class JsonVo<T> {
    int code;//状态码
    String msg;//失败信息
    int count;//解析目标字数

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    T data;//数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public JsonVo(int code,String msg,T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }
    public JsonVo(){};
}
