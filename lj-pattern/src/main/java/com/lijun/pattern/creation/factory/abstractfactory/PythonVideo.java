package com.lijun.pattern.creation.factory.abstractfactory;

/**
 * @author : LiJun
 * @date : 2020-03-23 17:40
 **/
public class PythonVideo implements IVideo {
    @Override
    public void record() {
        System.out.println("录制python视频");
    }
}
