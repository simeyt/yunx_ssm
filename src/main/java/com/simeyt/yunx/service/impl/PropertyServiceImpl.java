package com.simeyt.yunx.service.impl;

import com.simeyt.yunx.mapper.PropertyMapper;
import com.simeyt.yunx.pojo.Property;
import com.simeyt.yunx.pojo.PropertyExample;
import com.simeyt.yunx.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PropertyServiceImpl implements PropertyService
{
    @Autowired
    PropertyMapper propertyMapper;
    @Override
    public void add(Property property) {
        propertyMapper.insert(property);
    }

    @Override
    public void delete(int id) {
        propertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Property property) {
        propertyMapper.updateByPrimaryKey(property);
    }

    @Override
    public Property get(int id) {
        return propertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int cid) {
        PropertyExample example = new PropertyExample();
        example.createCriteria().andCidEqualTo(cid);//表示查询cid字段
        example.setOrderByClause("id asc");
        return propertyMapper.selectByExample(example);
    }
}
