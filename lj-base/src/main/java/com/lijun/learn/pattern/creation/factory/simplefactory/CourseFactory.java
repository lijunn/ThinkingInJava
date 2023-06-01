package com.lijun.learn.pattern.creation.factory.simplefactory;

import com.lijun.learn.pattern.creation.factory.ICourse;

/**
 * @author : LiJun
 * @date : 2020-03-23 16:41
 * 简单工厂
 **/
public class CourseFactory {


    public static ICourse create(Class<? extends ICourse> clazz){
        try {
            if (null != clazz){
                return clazz.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
