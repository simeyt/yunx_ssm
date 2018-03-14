package com.simeyt.yunx.mapper;

import com.simeyt.yunx.pojo.Admin;

public interface AdminMapper {
    public Admin getAdmin(String name,String password);
}
