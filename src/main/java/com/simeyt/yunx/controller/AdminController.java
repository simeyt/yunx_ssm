package com.simeyt.yunx.controller;

import com.simeyt.yunx.pojo.Admin;
import com.simeyt.yunx.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class AdminController {
    @Autowired
    AdminService adminService;
    @RequestMapping("admin_login")
    public String login(Admin admin,HttpSession session){
        boolean result = adminService.login(admin.getName(),admin.getPassword());
        if(result){
            session.setAttribute("admin",admin);
            return "redirect:admin_category_list";
        }else {
            return "admin/loginAdmin";
        }
    }
    @RequestMapping("admin_loginout")
    public String loginout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:admin_login";
    }
}
