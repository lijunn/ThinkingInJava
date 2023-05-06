package com.lijun.pattern.creation.factory;

/**
 * @author : LiJun
 * @date : 2020-03-23 16:40
 **/
public class PythonCourse implements ICourse {

    @Override
    public void recordVideo() {
        System.out.println("录制Python课程");
    }
}
