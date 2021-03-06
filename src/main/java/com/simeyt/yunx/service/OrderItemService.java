package com.simeyt.yunx.service;

import com.simeyt.yunx.pojo.Order;
import com.simeyt.yunx.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    public void add(OrderItem orderItem);

    public void delete(int id);

    public void update(OrderItem orderItem);

    public OrderItem get(int id);

    List<OrderItem> list();

    public void fill(List<Order> os);//

    public void fill(Order order);

    int getSaleCount(int pid);//根据产品获取销售量的方法

    List<OrderItem> listByUser(int uid);//根据用户id获取订单项的方法
}
