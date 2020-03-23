package com.lijun.learn.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

/**
 * @author : LiJun
 * @date : 2019-07-10 12:52
 * 原子类
 **/
public class AtomicTest {

    /**使用 volatile 修饰不能保证 volatileCount = volatileCount + 1 线程安全;*/
    private volatile static int volatileCount;
    private static int count;

    private static AtomicInteger atomicCount = new AtomicInteger();

    public static void main(String[] args){

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0;i<1000;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //非原子性操作
                    count++;
                    //原子性操作
                    atomicCount.getAndIncrement();
                    //非原子性操作
                    volatileCount = volatileCount + 1;
                }
            });
        }

        //等待上面任务执行完成
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //打印出线程池信息，检查线程是否执行完了
        println(executorService.toString());
        //volatileCount 和 count 的值有可能小于 1000
        println("count= "+count);
        println("atomicCount= "+atomicCount.get());
        println("volatileCount= "+volatileCount);
    }
}
