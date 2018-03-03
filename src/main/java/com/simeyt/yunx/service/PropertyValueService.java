package com.simeyt.yunx.service;

import com.simeyt.yunx.pojo.Product;
import com.simeyt.yunx.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {
    public void init(Product p);//初始化PropertyValue

    public void update(PropertyValue pv);

    PropertyValue get(int ptid,int pid);

    List<PropertyValue> list(int pid);
}
