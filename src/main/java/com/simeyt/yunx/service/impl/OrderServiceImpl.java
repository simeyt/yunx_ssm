package com.simeyt.yunx.service.impl;

import com.simeyt.yunx.mapper.OrderMapper;
import com.simeyt.yunx.pojo.Order;
import com.simeyt.yunx.pojo.OrderExample;
import com.simeyt.yunx.pojo.User;
import com.simeyt.yunx.service.OrderService;
import com.simeyt.yunx.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Override
    public void add(Order order) {
        orderMapper.insertSelective(order);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> list() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> result = orderMapper.selectByExample(example);
        setUser(result);
        return result;
    }

    public void setUser(Order order){
        int uid= order.getUid();
        User u = userService.get(uid);
        order.setUser(u);
    }

    public void setUser(List<Order> os){
        for(Order o : os){
            setUser(o);
        }
    }
}
