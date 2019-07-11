package com.thinking.in.java.chapter21;

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
                }
            });
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //打印出线程池信息，检查线程是否执行完了
        println(executorService.toString());
        //打印count值，count 的值有可能小于 1000
        println("count= "+count);
        println("atomicCount= "+atomicCount.get());
    }






}
