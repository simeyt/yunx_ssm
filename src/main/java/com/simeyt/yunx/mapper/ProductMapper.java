package com.simeyt.yunx.mapper;

import com.simeyt.yunx.pojo.Product;
import com.simeyt.yunx.pojo.ProductExample;
import java.util.List;
import java.util.Map;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectBySale();

    List<Product> selectByPrice(Map<String,Object> map);
}