package com.lijun.pattern.creation.factory.methodfactory;

import com.lijun.pattern.creation.factory.ICourse;

/**
 * @author : LiJun
 * @date : 2020-03-23 17:17
 * 方法工厂
 * 优点：解决了简单工厂单一职责的问题，将不同产品创建的流程放到不同工厂中，
 *      增加新产品时只要增加相应的工厂即可，无需修改代码
 * 缺点：类的个数容易过多，增加了系统的抽象性和复杂性，不能解决多维度的产品(抽象工厂可以解决)
 **/
public class MethodFactoryTest {

    public static void main(String[] args) {

        JavaCourseFactory javaCourseFactory = new JavaCourseFactory();
        PythonCourseFactory pythonCourseFactory = new PythonCourseFactory();

        ICourse javaCourse = javaCourseFactory.createCourse();
        ICourse pythonCourse = pythonCourseFactory.createCourse();

        javaCourse.recordVideo();
        pythonCourse.recordVideo();
    }
}
