package com.lijun.pattern.factory.methodfactory;

import com.lijun.pattern.factory.ICourse;
import com.lijun.pattern.factory.JavaCourse;

/**
 * @author : LiJun
 * @date : 2020-03-23 17:13
 **/
public class JavaCourseFactory implements ICourseFactory {

    @Override
    public ICourse createCourse() {
        return new JavaCourse();
    }
}
