package com.lijun.pattern.creation.factory.abstractfactory;

/**
 * @author : LiJun
 * @date : 2020-03-23 17:41
 **/
public class JavaCourseFactory implements CourseFactory {
    @Override
    public IVideo createVideo() {
        return new JavaVideo();
    }

    @Override
    public INote createNote() {
        return new JavaNote();
    }
}
