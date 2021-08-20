package com.lj.mybatis;

import com.alibaba.fastjson.JSON;
import com.lj.mybatis.mapper.UserMapper;
import com.lj.mybatis.vo.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@MapperScan({"com.lj.mybatis.mapper"})
@SpringBootApplication
public class LjMybatisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LjMybatisApplication.class, args);

        UserMapper userMapper = context.getBean(UserMapper.class);
        User user = userMapper.getUserById(1L);

        System.out.println(JSON.toJSONString(user,true));
    }

}
