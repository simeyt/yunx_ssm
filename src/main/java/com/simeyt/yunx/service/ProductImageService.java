package com.simeyt.yunx.service;

import com.simeyt.yunx.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {
    String type_single = "type_single";//单个图片
    String typt_detail = "type_detail";//详情图片

    public void add(ProductImage productImage);

    public void delete(int id);

    public void update(ProductImage productImage);

    ProductImage get(int id);

    List list(int pid,String type);//根据产品id和图片类型查询的list方法
}
