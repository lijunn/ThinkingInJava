package com.lijun.pattern.factory;

/**
 * @author : LiJun
 * @date : 2020-03-23 16:40
 **/
public class JavaCourse implements ICourse {

    @Override
    public void recordVideo() {
        System.out.println("录制Java课程");
    }
}
