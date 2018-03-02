package com.simeyt.yunx.service;

import com.simeyt.yunx.pojo.Category;
import com.simeyt.yunx.util.Page;

import java.util.List;

public interface CategoryService{
    public void add(Category category);

    public void delete(int id);

    public void update(Category category);

    Category get (int id);

    List<Category> list();
}
