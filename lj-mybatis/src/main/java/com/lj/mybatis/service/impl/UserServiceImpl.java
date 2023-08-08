package com.lj.mybatis.service.impl;

import com.lj.mybatis.mapper.UserMapper;
import com.lj.mybatis.service.UserService;
import com.lj.mybatis.vo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author : LiJun
 * @date : 2021-08-20 11:39
 **/
@Service
//@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id){
        return userMapper.getUserById(id);
    }

    @Override
    public void addUser(User user)  {
        userMapper.addUser(user);
    }


}
