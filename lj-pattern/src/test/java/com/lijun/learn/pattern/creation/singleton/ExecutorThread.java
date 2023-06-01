package com.lijun.learn.pattern.creation.singleton;

import com.lijun.learn.pattern.creation.singleton.threadlocal.ThreadLocalSingleton;

/**
 * @author : LiJun
 * @date : 2020-03-26 11:40
 **/
public class ExecutorThread implements Runnable {
    @Override
    public void run() {
        //双重检查
//        LazyDoubleCheckSingleton instance = LazyDoubleCheckSingleton.getInstance();

        //静态内部类单例
//        LazyInnerClassSingleton instance = LazyInnerClassSingleton.getInstance();

        //枚举单例
//        EnumSingleton instance = EnumSingleton.getInstance();

//        System.out.println(instance);


        //ThreadLocal 单例 ,同一个线程每次获取的都是同一个对象
        System.out.println(Thread.currentThread().getName()+":"+ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName()+":"+ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName()+":"+ThreadLocalSingleton.getInstance());

    }
}
