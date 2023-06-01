package com.lijun.learn.pattern.creation.singleton.lazy;

/**
 * @author : LiJun
 * @date : 2020-03-26 11:50
 * 静态内部类单例原理：
 * 1.懒加载 : 将静态内部类设置为 private ，外界无法访问，所以只有在调用 getInstance() 内部类才能被加载，
 *          LazyInnerClassSingleton 才能被实例化。
 * 2.线程安全 : 因为JVM类加载是线程安全的，所以用静态字段加载对象是线程安全的
 *
 * 优点：懒加载，没有加锁效率高
 * 缺点：可以被反射调用构造函数破解单例，其他单例也一样，这个问题可以用注册式单例来解决
 *
 * 注意事项：
 *      1.静态内部类要设置为 private
 **/
public class LazyInnerClassSingleton {

    private LazyInnerClassSingleton() {
    }

    private static class InnerClass{
        static LazyInnerClassSingleton instance = new LazyInnerClassSingleton();
    }


    public static LazyInnerClassSingleton getInstance(){
        return InnerClass.instance;
    }

}
