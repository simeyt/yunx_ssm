package com.simeyt.yunx.service;

import com.simeyt.yunx.pojo.Property;

import java.util.List;

public interface PropertyService
{
    public void add(Property property);

    public void delete(int id);

    public void update(Property property);

    Property get(int id);

    List list(int cid);//属性要通过分类的ID获取
}
