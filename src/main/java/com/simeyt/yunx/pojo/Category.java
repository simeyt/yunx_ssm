package com.simeyt.yunx.pojo;

import java.util.List;

public class Category {
    private Integer id;

    private String name;

    /*以下是非逆向工程字段*/
    List<Product> products;

    List<List<Product>> productsByRow;//一个分类会对应多行产品，而一行产品里又有多个产品记录。

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<List<Product>> getProductsByRow() {
        return productsByRow;
    }

    public void setProductsByRow(List<List<Product>> productsByRow) {
        this.productsByRow = productsByRow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}