package com.simeyt.yunx.service.impl;

import com.simeyt.yunx.mapper.UserMapper;
import com.simeyt.yunx.pojo.User;
import com.simeyt.yunx.pojo.UserExample;
import com.simeyt.yunx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public void delete(int id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User get(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> list() {
        UserExample example= new UserExample();
        example.setOrderByClause("id asc");
        return userMapper.selectByExample(example);
    }
}
