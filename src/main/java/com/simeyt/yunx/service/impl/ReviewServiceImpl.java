package com.simeyt.yunx.service.impl;

import com.simeyt.yunx.mapper.ReviewMapper;
import com.simeyt.yunx.pojo.Review;
import com.simeyt.yunx.pojo.ReviewExample;
import com.simeyt.yunx.pojo.User;
import com.simeyt.yunx.service.ReviewService;
import com.simeyt.yunx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;
    @Override
    public void add(Review review) {
        reviewMapper.insert(review);
    }

    @Override
    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Review review) {
        reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public Review get(int id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Review> list(int pid) {
        ReviewExample example = new ReviewExample();
        example.createCriteria().andPidEqualTo(pid);

        List<Review> result = reviewMapper.selectByExample(example);
        setUser(result);//为评价设置用户
        return result;
    }

    @Override
    public int getCount(int pid) {
        return list(pid).size();
    }
    //为评价设置用户
    public void setUser(Review review){
        int uid = review.getUid();
        User user = userService.get(uid);
        review.setUser(user);
    }
    //为评价设置用户
    public void setUser(List<Review> reviews){
        for(Review review : reviews){
            setUser(review);
        }
    }
}
