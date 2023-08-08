package com.lj.mybatis.service.impl;

import com.lj.mybatis.mapper.UserMapper;
import com.lj.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;


    @Test
    void getUserById() {
        userMapper.getUserById(1L);

        userMapper.getUserById(1L);
    }

    @Test
    void getUserById2() {
        userService.getUserById(1L);

        userService.getUserById(1L);
    }
}
