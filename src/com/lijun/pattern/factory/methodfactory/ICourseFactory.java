package com.lijun.pattern.factory.methodfactory;

import com.lijun.pattern.factory.ICourse;

/**
 * @author : LiJun
 * @date : 2020-03-23 17:08
 * 方法工厂
 **/
public interface ICourseFactory {

//    /**
//     * 创建前预处理
//     */
//    public void createProcess(){
//        System.out.println("创建预处理");
//    }


    /**
     * 创建课程
     */
     ICourse createCourse();
}
