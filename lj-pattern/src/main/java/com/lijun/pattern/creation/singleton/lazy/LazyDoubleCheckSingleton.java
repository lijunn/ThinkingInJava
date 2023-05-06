package com.lijun.pattern.creation.singleton.lazy;

/**
 * @author : LiJun
 * @date : 2020-03-25 17:55
 * 双重检查
 *
 * <1>.使用 synchronized 代码块加锁，缩小被锁住的范围，提高效率
 * <2>.双重检查，提高效率，外层检查可以避免每次获取对象都访问锁
 * <3>.双重检查引发的另一个问题
 * 对象创建的底层是分为多个步骤的
 *      1.分配内存空间
 *      2.初始化对象
 *      3.将引用指向对象内存地址
 * 正常情况下执行顺序应该为 1->2->3,但是如果出现指令重排序时
 * 执行顺序变成 1->3->2 那么就会出现对象还未初始化完成时，INSTANCE 引用已经不为null了，
 * 这样当B线程执行到2号位置，正在创建对象时， A 线程执行到1号位置就会直接返回一个还未初始化完成的对象。
 *
 **/
public class LazyDoubleCheckSingleton {

    /**
     * 加上 volatile 防止指令重排序
     */
    private static volatile LazyDoubleCheckSingleton instance;

    private LazyDoubleCheckSingleton(){}

    public static LazyDoubleCheckSingleton getInstance(){

        /**1号位置，A线程执行到这里*/
        if (null == instance) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (null == instance) {
                    /**2号位置，B线程执行到这里*/
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }

        return instance;
    }
}
