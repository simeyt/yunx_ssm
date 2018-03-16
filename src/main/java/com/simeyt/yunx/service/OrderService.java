package com.simeyt.yunx.service;

import com.simeyt.yunx.pojo.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {
    String waitPay = "waitPay";// 等待支付
    String waitDelivery = "waitDelivery";// 等待发货
    String waitConfirm = "waitConfirm";// 等待收货
    String waitReview = "waitReview";// 等待评价
    String finish = "finish";// 完成订单
    String delete = "delete";// 删除订单

    public void add(Order order);

    public void delete(int id);

    public void update(Order order);

    public Order get(int id);

    List<Order> list();
}
