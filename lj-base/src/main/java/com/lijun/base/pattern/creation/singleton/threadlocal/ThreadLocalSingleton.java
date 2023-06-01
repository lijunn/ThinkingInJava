package com.lijun.base.pattern.creation.singleton.threadlocal;

import java.util.function.Supplier;

/**
 * @author : LiJun
 * @date : 2020-03-27 09:51
 *
 * ThreadLocal 实现单例
 *
 * 只是单个线程的单例模式，可以实现线程隔离，也就是每个线程都包存有自己的一个单例
 * 不同的线程获取的不是同一个对象
 *
 * 原理：
 *
 *      ThreadLocal 的原理是在 Thread 对象中存放一个 ThreadLocalMap<ThreadLocal<?> k, Object v> 集合，
 *      其中 k 是ThreadLocal本身， v 是要存放的对象。
 *      因为是每个线程都单独持有一个 Map 所以每个线程是相互隔离的
 *
 **/
public class ThreadLocalSingleton {


    private static ThreadLocal<ThreadLocalSingleton> threadLocal = ThreadLocal.withInitial(new Supplier<ThreadLocalSingleton>() {
        @Override
        public ThreadLocalSingleton get() {
            return new ThreadLocalSingleton();
        }
    });

    private ThreadLocalSingleton() {
    }

    public static ThreadLocalSingleton getInstance(){
        return threadLocal.get();
    }

}
