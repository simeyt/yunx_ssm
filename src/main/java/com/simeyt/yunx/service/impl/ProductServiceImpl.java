package com.simeyt.yunx.service.impl;

import com.simeyt.yunx.mapper.CategoryMapper;
import com.simeyt.yunx.mapper.ProductMapper;
import com.simeyt.yunx.pojo.Category;
import com.simeyt.yunx.pojo.Product;
import com.simeyt.yunx.pojo.ProductExample;
import com.simeyt.yunx.service.CategoryService;
import com.simeyt.yunx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryService categoryService;
    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }
// get和list方法都会把取出来的Product对象设置上对应的category
    public void setCategory(List<Product> ps){
        for (Product p : ps)
            setCategory(p);
    }

    public void setCategory(Product p){
        int cid = p.getCid();
        Category c= categoryService.get(cid);
        p.setCategory(c);
    }

    @Override
    public Product get(int id) {
        Product product = productMapper.selectByPrimaryKey(id);
        setCategory(product);
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int cid) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);//表示查询cid字段
        example.setOrderByClause("id asc");
        return productMapper.selectByExample(example);
    }
}
