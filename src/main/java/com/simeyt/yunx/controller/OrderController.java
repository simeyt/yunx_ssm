package com.simeyt.yunx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simeyt.yunx.pojo.Order;
import com.simeyt.yunx.service.OrderItemService;
import com.simeyt.yunx.service.OrderService;
import com.simeyt.yunx.util.Page;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @RequestMapping("admin_order_list")
    public String list(Model model, Page page){//查询订单
        PageHelper.offsetPage(page.getStart(),page.getCount());

        List<Order> os = orderService.list();
        int total = (int) new PageInfo<>(os).getTotal();
        page.setTotal(total);

        orderItemService.fill(os);

        model.addAttribute("os",os);
        model.addAttribute("page",page);
        return "admin/listOrder";
    }
   @RequestMapping("admin_order_delivery")
    public String delivery(Order order){//发货
        order.setDeliveryDate(new Date());
        order.setStatus(OrderService.waitConfirm);
        orderService.update(order);
        return "redirect:admin_order_list";
    }
}
