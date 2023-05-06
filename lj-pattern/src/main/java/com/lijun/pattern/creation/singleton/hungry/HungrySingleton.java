package com.lijun.pattern.creation.singleton.hungry;

/**
 * @author : LiJun
 * @date : 2020-03-24 16:29
 * 饿汉单例模式
 * 优点：没有加锁效率高，类加载时就创建，线程安全
 * 缺点：浪费内存，在不使用的时候就创建了
 *
 **/
public class HungrySingleton {

    /**需要添加 final 防止被反射修改*/
    private static final HungrySingleton instance = new HungrySingleton();

    /**私有化构造函数*/
    private HungrySingleton() {
    }

    public static HungrySingleton getInstance(){
        return instance;
    }
}
