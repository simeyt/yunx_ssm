package com.simeyt.yunx.util;

import com.simeyt.yunx.mapper.AdminMapper;
import com.simeyt.yunx.mapper.ProductMapper;
import com.simeyt.yunx.mapper.UserMapper;
import com.simeyt.yunx.pojo.*;
import com.simeyt.yunx.service.OrderItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpring {
    @Autowired
    OrderItemService orderItemService;
    @Test
    public void test(){
        List<OrderItem> orderItems = orderItemService.listByUser(5);

    }
}
