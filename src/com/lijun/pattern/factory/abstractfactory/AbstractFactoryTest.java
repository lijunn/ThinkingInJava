package com.lijun.pattern.factory.abstractfactory;

/**
 * @author : LiJun
 * @date : 2020-03-23 17:43
 * 抽象工厂
 * 优点：解决了多维度产品的复杂问题，即Java课程是一个产品族里面包含 视频和比较两个产品，同样Python课程也是一个产品族
 * 缺点：规定了可能被创建的产品集合，产品族中有新的产品扩展时需要修改代码
 **/
public class AbstractFactoryTest {
    public static void main(String[] args) {
        JavaCourseFactory javaCourseFactory = new JavaCourseFactory();
        javaCourseFactory.createNote();
        javaCourseFactory.createVideo();


        PythonCourseFactory pythonCourseFactory = new PythonCourseFactory();
        pythonCourseFactory.createNote();
        pythonCourseFactory.createVideo();
    }
}
