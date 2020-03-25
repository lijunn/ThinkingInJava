package com.lijun.pattern.singleton.lazy;

/**
 * @author : LiJun
 * @date : 2020-03-25 09:37
 * 懒汉式简单单例，需要在方法上加 synchronized 保证线程安全,
 * synchronized 加在方法上表示整个对象中所有加了 synchronized 的方法用的都是同一个锁，即 LazySimpleSingleton.class
 * 那么也就是多线程每次只能够访问一个方法，效率低
 **/
public class LazySimpleSingleton {

    private static LazySimpleSingleton INSTANCE;

    private LazySimpleSingleton(){}

    public static synchronized LazySimpleSingleton getInstance(){
        if (null == INSTANCE){
            INSTANCE = new LazySimpleSingleton();
        }
        return INSTANCE;
    }

}
