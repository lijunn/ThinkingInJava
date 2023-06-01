package com.lijun.base.pattern.creation.factory.methodfactory;

import com.lijun.base.pattern.creation.factory.ICourse;
import com.lijun.base.pattern.creation.factory.PythonCourse;

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
