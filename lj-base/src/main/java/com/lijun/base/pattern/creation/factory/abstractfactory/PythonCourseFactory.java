package com.lijun.base.pattern.creation.factory.abstractfactory;

/**
 * @author : LiJun
 * @date : 2020-03-23 17:41
 **/
public class PythonCourseFactory implements CourseFactory {
    @Override
    public IVideo createVideo() {
        return new PythonVideo();
    }

    @Override
    public INote createNote() {
        return new PythonNote();
    }
}
