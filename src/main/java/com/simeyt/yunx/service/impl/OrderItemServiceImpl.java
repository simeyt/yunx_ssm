package com.simeyt.yunx.service.impl;

import com.simeyt.yunx.mapper.OrderItemMapper;
import com.simeyt.yunx.pojo.Order;
import com.simeyt.yunx.pojo.OrderItem;
import com.simeyt.yunx.pojo.OrderItemExample;
import com.simeyt.yunx.pojo.Product;
import com.simeyt.yunx.service.OrderItemService;
import com.simeyt.yunx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;
    @Override
    public void add(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }

    @Override
    public void delete(int id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }

    @Override
    public OrderItem get(int id) {
        OrderItem result = orderItemMapper.selectByPrimaryKey(id);
        setProduct(result);
        return result;
    }
    public void setProduct(OrderItem orderItem){//为订单项设置商品
        Product p = productService.get(orderItem.getPid());
        orderItem.setProduct(p);
    }

    public void setProduct(List<OrderItem> ois){////为订单项设置商品
        for(OrderItem oi : ois){
            setProduct(oi);
        }
    }

    @Override
    public List<OrderItem> list() {
        OrderItemExample example = new OrderItemExample();
        example.setOrderByClause("id desc");
        return orderItemMapper.selectByExample(example);
    }

    @Override
    public void fill(List<Order> os) {
        for(Order o : os){
            fill(o);
        }
    }

    @Override
    public void fill(Order order) {
//        1. 根据订单id查询出其对应的所有订单项
//        2. 通过setProduct为所有的订单项设置Product属性
//        3. 遍历所有的订单项，然后计算出该订单的总金额和总数量
//        4. 最后再把订单项设置在订单的orderItems属性上。
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andOidEqualTo(order.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> ois = orderItemMapper.selectByExample(example);
        setProduct(ois);

        float total = 0;
        int totalNumber = 0;
        for(OrderItem oi : ois){
            total = total + oi.getNumber()*oi.getProduct().getPromotePrice();
            totalNumber = totalNumber + oi.getNumber();
        }
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(ois);
    }

    @Override
    public int getSaleCount(int pid) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andPidEqualTo(pid);
        List<OrderItem> ois = orderItemMapper.selectByExample(example);
        int result = 0;
        for(OrderItem oi:ois){
            result+=oi.getNumber();
        }
        return result;
    }
}
