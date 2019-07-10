package com.thinking.in.java.chapter21;

/**
 * @author : LiJun
 * @date : 2019-07-10 16:35
 *
 *
 * 变量声明为volatile，这就指示 JVM，这个变量是不稳定的，每次使用它都到主存中进行读取。
 * volatile 关键字的主要作用就是保证变量的可见性然后还有一个作用是防止指令重排序。
 **/
public class VolatileTest {


    /**
     * 双重校验单例模式
     */
    public static class SingleInstance{
        /**
         * 避免初始化操作的指令重排序
         */
        private volatile static SingleInstance instance;

        /**
         * 私有化默认构造函数,使得外部无法调用
         */
        private SingleInstance(){}

        public static SingleInstance getInstance(){
            //这一层判断是为了，不用每次都走 synchronized,提高性能
            if (instance == null){
                //类对象加锁
                synchronized(VolatileTest.class){
                    if (instance == null){
                        instance = new SingleInstance();
                    }
                }
            }

            return instance;
        }


//        public static class
    }



}
