package com.simeyt.yunx.service;

import com.simeyt.yunx.pojo.Category;
import com.simeyt.yunx.pojo.Product;

import java.util.List;

public interface ProductService {
    public void add(Product product);

    public void delete(int id);

    public void update(Product product);

    Product get(int id);

    List list(int cid);// 商品要通过分类的ID获取

    public void setFirstProductImage(Product product);

    // 前台服务

    public void fill(List<Category> cs);// 为多个分类填充产品集合

    public void fill(Category category);// 为分类填充产品集合

    public void fillByRow(List<Category> cs);// 为多个分类填充推荐产品集合，即把分类下的产品集合，按照8个为一行，拆成多行，以利于后续页面上进行显示

    void setSaleAndReviewNumber(Product product);// 为产品设置销量和评价数量的方法

    void setSaleAndReviewNumber(List<Product> products);// 为产品设置销量和评价数量的方法
}
