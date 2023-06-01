package com.lijun.base.pattern.creation.factory.methodfactory;

import com.lijun.base.pattern.creation.factory.ICourse;
import com.lijun.base.pattern.creation.factory.JavaCourse;

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
