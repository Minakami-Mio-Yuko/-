package com.miaomiao.wedding.demo.entity;

public class Order {
    Integer orderId;
    String orderManname;
    String orderWomanname;
    String orderAdress;
    String orderDate;
    String orderArea;
    String orderCity;
    String orderPhone;
    String orderWechat;
    String orderCameraman;
    Integer userId;
    String orderDesc;
    String orderStatus;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderManname() {
        return orderManname;
    }

    public void setOrderManname(String orderManname) {
        this.orderManname = orderManname;
    }

    public String getOrderWomanname() {
        return orderWomanname;
    }

    public void setOrderWomanname(String orderWomanname) {
        this.orderWomanname = orderWomanname;
    }

    public String getOrderAdress() {
        return orderAdress;
    }

    public void setOrderAdress(String orderAdress) {
        this.orderAdress = orderAdress;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderArea() {
        return orderArea;
    }

    public void setOrderArea(String orderArea) {
        this.orderArea = orderArea;
    }

    public String getOrderCity() {
        return orderCity;
    }

    public void setOrderCity(String orderCity) {
        this.orderCity = orderCity;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getOrderWechat() {
        return orderWechat;
    }

    public void setOrderWechat(String orderWechat) {
        this.orderWechat = orderWechat;
    }

    public String getOrderCameraman() {
        return orderCameraman;
    }

    public void setOrderCameraman(String orderCameraman) {
        this.orderCameraman = orderCameraman;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }
}
