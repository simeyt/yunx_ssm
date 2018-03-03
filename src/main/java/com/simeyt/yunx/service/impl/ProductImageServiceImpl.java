package com.simeyt.yunx.service.impl;

import com.simeyt.yunx.mapper.ProductImageMapper;
import com.simeyt.yunx.pojo.ProductImage;
import com.simeyt.yunx.pojo.ProductImageExample;
import com.simeyt.yunx.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageMapper productImageMapper;
    @Override
    public void add(ProductImage productImage) {
        productImageMapper.insert(productImage);
    }

    @Override
    public void delete(int id) {
        productImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ProductImage productImage) {
        productImageMapper.updateByPrimaryKeySelective(productImage);
    }

    @Override
    public ProductImage get(int id) {
        return productImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int pid, String type) {
        ProductImageExample example = new ProductImageExample();
        example.createCriteria().andPidEqualTo(pid).andTypeEqualTo(type);
        example.setOrderByClause("id asc");
        return productImageMapper.selectByExample(example);
    }
}
