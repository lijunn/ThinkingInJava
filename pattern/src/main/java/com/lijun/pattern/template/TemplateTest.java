package com.lijun.pattern.template;

import com.lijun.pattern.template.dao.UserDao;

import java.util.List;

/**
 * @author : LiJun
 * @date : 2020-04-07 16:38
 * 模板方法设计模式
 *
 **/
public class TemplateTest {

    public static void main(String[] args) {
        SimpleDataSource dataSource = new SimpleDataSource();
        dataSource.setUrl("jdbc:mysql://139.224.1.155:3306/teeth?useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false&testOnBorrow=true&validationQuery=select 1");
        dataSource.setUser("comma-admin");
        dataSource.setPassword("comma-admin");


        UserDao userDao = new UserDao(dataSource);
        List<User> objects = userDao.selectAll();

        System.out.println(objects);

    }
}
