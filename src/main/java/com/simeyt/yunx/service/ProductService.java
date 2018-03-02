package com.simeyt.yunx.service;

import com.simeyt.yunx.pojo.Product;

import java.util.List;

public interface ProductService {
    public void add(Product product);

    public void delete(int id);

    public void update(Product product);

    Product get(int id);

    List list(int cid);//商品要通过分类的ID获取
}
