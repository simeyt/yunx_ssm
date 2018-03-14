package com.simeyt.yunx.controller;

import com.simeyt.yunx.pojo.Admin;
import com.simeyt.yunx.service.AdminService;
import com.simeyt.yunx.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("")
public class AdminController {
    @Autowired
    AdminService adminService;
    @RequestMapping("admin_login")
    public String login(Admin admin, HttpServletRequest request){
        boolean loginType = adminService.login(admin.getName(),admin.getPassword());
        if(loginType){
            request.setAttribute("admin",admin);
            return "admin/listCategory";
        }else {
            request.setAttribute("message","登陆失败!");
            System.out.println("登陆失败");
            return "admin/error";
        }
    }
}
