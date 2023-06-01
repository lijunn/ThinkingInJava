package com.lijun.learn.pattern.creation.factory.simplefactory;

import com.lijun.learn.pattern.creation.factory.ICourse;
import com.lijun.learn.pattern.creation.factory.JavaCourse;
import com.lijun.learn.pattern.creation.factory.PythonCourse;

/**
 * @author : LiJun
 * @date : 2020-03-23 16:41
 * 优点：封装产品创建的细节，客户端只需要传入相关的参数，无需关心创建细节，
 *
 * 缺点：不符合单一职责原则，工厂类承担太多职责,不利于扩展复杂的产品结构，
 *      添加新的产品时需要修改工厂代码。
 **/
public class SimpleFactoryTest {

    public static void main(String[] args) {

        ICourse javaCourse = CourseFactory.create(JavaCourse.class);
        ICourse pythonCourse = CourseFactory.create(PythonCourse.class);

        javaCourse.recordVideo();
        pythonCourse.recordVideo();

    }
}
