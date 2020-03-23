package com.lijun.pattern.factory.abstractfactory;

/**
 * @author : LiJun
 * @date : 2020-03-23 17:40
 **/
public class JavaVideo implements IVideo {
    @Override
    public void record() {
        System.out.println("录制java视频");
    }
}
