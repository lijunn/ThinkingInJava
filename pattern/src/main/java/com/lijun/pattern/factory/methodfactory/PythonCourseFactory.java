package com.lijun.pattern.factory.methodfactory;

import com.lijun.pattern.factory.ICourse;
import com.lijun.pattern.factory.JavaCourse;
import com.lijun.pattern.factory.PythonCourse;

/**
 * @author : LiJun
 * @date : 2020-03-23 17:13
 **/
public class PythonCourseFactory implements ICourseFactory {

    @Override
    public ICourse createCourse() {
        return new PythonCourse();
    }
}
