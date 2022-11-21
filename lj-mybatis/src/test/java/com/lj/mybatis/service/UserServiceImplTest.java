package com.lj.mybatis.service;

import cn.hutool.json.JSONUtil;
import com.lj.mybatis.LjMybatisApplication;
import com.lj.mybatis.vo.User;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Slf4j

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest(classes = LjMybatisApplication.class)
public class UserServiceImplTest extends TestCase {

    @Autowired
    private UserService userService;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void getUserById() {
        User user = userService.getUserById(1L);
        log.info("getUserById:{}", JSONUtil.toJsonPrettyStr(user));
    }

    @Test
    public void addUser()  {
        User user = new User();
        user.setName("AAa");
        user.setAge(11);
        userService.addUser(user);
        log.info("addUser:{}", JSONUtil.toJsonPrettyStr(user));

//        throw new  Exception("sss");
    }

    @Test
    public void test(){
        jdbcTemplate.execute(" insert into user(name,age) value ('sss',12)");
    }

}
