package com.lijun.learn.pattern.creation.factory.methodfactory;

import com.lijun.learn.pattern.creation.factory.ICourse;
import com.lijun.learn.pattern.creation.factory.PythonCourse;

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
