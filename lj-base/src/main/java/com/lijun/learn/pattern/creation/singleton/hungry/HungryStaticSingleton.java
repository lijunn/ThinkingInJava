package com.lijun.learn.pattern.creation.singleton.hungry;

/**
 * @author : LiJun
 * @date : 2020-03-24 16:29
 * 饿汉单例模式
 * 使用静态代码块
 **/
public class HungryStaticSingleton {

    /**需要添加 final 防止被反射修改*/
    private static final HungryStaticSingleton instance;

    static {
        instance = new HungryStaticSingleton();
    }

    private HungryStaticSingleton() {
    }

    public static HungryStaticSingleton getInstance(){
        return instance;
    }
}
