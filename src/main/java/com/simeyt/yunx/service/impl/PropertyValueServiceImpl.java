package com.simeyt.yunx.service.impl;

import com.simeyt.yunx.mapper.PropertyValueMapper;
import com.simeyt.yunx.pojo.*;
import com.simeyt.yunx.service.PropertyService;
import com.simeyt.yunx.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PropertyValueServiceImpl implements PropertyValueService{
    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    PropertyService propertyService;
    @Override
    public void init(Product p) {
        List<Property> pts = propertyService.list(p.getCid());

        for(Property pt : pts){
            PropertyValue pv = get(pt.getId(),p.getId());//获得属性值
            if(pv==null){//空的话就要初始化
                pv = new PropertyValue();
                pv.setPid(p.getId());
                pv.setPtid(pt.getId());
                propertyValueMapper.insert(pv);
            }
        }
    }

    @Override
    public void update(PropertyValue pv) {
        propertyValueMapper.updateByPrimaryKeySelective(pv);
    }

    @Override
    public PropertyValue get(int ptid, int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPtidEqualTo(ptid).andPidEqualTo(pid);
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        if(pvs.isEmpty()){
            return null;
        }
        return pvs.get(0);
    }

    @Override
    public List<PropertyValue> list(int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> result = propertyValueMapper.selectByExample(example);
        for(PropertyValue pv : result){
            Property property = propertyService.get(pv.getPtid());
            pv.setProperty(property);// 设置对应的属性
        }
        return result;
    }
}
