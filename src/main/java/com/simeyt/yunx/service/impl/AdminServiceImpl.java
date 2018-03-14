package com.simeyt.yunx.service.impl;

import com.simeyt.yunx.mapper.AdminMapper;
import com.simeyt.yunx.pojo.Admin;
import com.simeyt.yunx.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public boolean login(String name, String password) {
        Admin admin = adminMapper.getAdmin(name, password);
        if(admin != null){
            if(admin.getName().equals(name)&&admin.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
