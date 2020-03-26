package com.lijun.pattern.singleton;

import com.lijun.pattern.singleton.lazy.LazyDoubleCheckSingleton;
import com.lijun.pattern.singleton.lazy.LazyInnerClassSingleton;
import com.lijun.pattern.singleton.register.EnumSingleton;

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
        EnumSingleton instance = EnumSingleton.getInstance();

        System.out.println(instance);
    }
}
