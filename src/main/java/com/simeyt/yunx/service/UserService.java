package com.simeyt.yunx.service;

import com.simeyt.yunx.pojo.User;

import java.util.List;

public interface UserService {
    public void add(User user);

    public void delete(int id);

    public void update(User user);

    public User get(int id);

    List<User> list();

}
