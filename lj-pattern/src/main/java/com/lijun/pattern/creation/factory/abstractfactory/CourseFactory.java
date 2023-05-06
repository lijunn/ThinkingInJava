package com.lijun.pattern.creation.factory.abstractfactory;

/**
 * @author : LiJun
 * @date : 2020-03-23 17:34
 **/
public interface CourseFactory {

    /**
     * 创建课堂视频
     * @return IVideo
     */
    IVideo createVideo();

    /**
     * 创建课堂笔记
     * @return INote
     */
    INote createNote();
}
