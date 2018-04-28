package com.simeyt.yunx.mapper;

import com.simeyt.yunx.pojo.Admin;


public interface AdminMapper {
    Admin selectByName(String username);
}
