package com.lijun.pattern.singleton.hungry;

/**
 * @author : LiJun
 * @date : 2020-03-24 16:29
 * 饿汉单例模式
 * 使用静态代码块
 **/
public class HungryStaticSingleton {

    private static HungryStaticSingleton INSTANCE;

    {
        INSTANCE = new HungryStaticSingleton();
    }

    private HungryStaticSingleton() {
    }

    public static HungryStaticSingleton getInstance(){
        return INSTANCE;
    }
}
