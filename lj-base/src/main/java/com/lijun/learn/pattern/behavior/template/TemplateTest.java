package com.lijun.learn.pattern.behavior.template;

import com.lijun.learn.pattern.behavior.template.dao.UserDao;

import java.util.List;

/**
 * @author : LiJun
 * @date : 2020-04-07 16:38
 * 模板方法设计模式
 *
 * 用抽象类的方式，对固定的流程进行抽取，限定这些流程的执行顺序
 * 可以使用未实现的方法作为钩子方法，从而实现对流程的微调
 * 这个模板类就可以当做一个模板，子类继承模板
 *
 * 模板会有一个或者多个未现实方法，
 * 而且这几个未实现方法有固定的执行循序
 *
 * 优点：
 * 1.利用模板方法将共同的代码抽取到父类中提高了代码的复用性
 * 2.利用模板方法设定了执行流程，防止子类对流程的修改
 * 3.不同子类通过重写抽象方法可以完成不同的实现，提高了扩展性，符合开闭原则
 *
 * 缺点：
 * 1.类的数目增加，每一个抽象类都需要实现一个子类，增加了系统的复杂度
 * 2.继承关系的缺点，如果父类添加一个抽象方法，所有子类都需要实现
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
