package com.lijun.learn.concurrent.thread;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : LiJun
 * @date : 2019-07-08 15:02
 **/
public class ThreadLocalTest {


    /**初始化ThreadLocal, 存放 SimpleDateFormat ,SimpleDateFormat 不是线程安全的，所以每个线程都要有自己独立的副本*/
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(()->new SimpleDateFormat("yyyyMMdd HHmm"));

    public static void main(String[] args){

        //用Executors创建的线程池 ， 允许创建的线程数量为 Integer.MAX_VALUE ，可能会创建大量线程，从而导致OOM。所以尽量手动创建
        ExecutorService executorService = Executors.newCachedThreadPool();


        for (int i=0 ; i<10; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //打印默认的 formatter
                    System.out.println("Thread Name= "+Thread.currentThread().getName()+" default Formatter = "+formatter.get().toPattern());

                    threadSleep();
                    //设置 当前线程私有的 formatter ，只会改版当前线程的 formatter，其他线程的不受影响
                    formatter.set(new SimpleDateFormat());
                    //打印当前线程的 formatter
                    System.out.println("Thread Name= "+Thread.currentThread().getName()+" formatter = "+formatter.get().toPattern());
                }
            });

            threadSleep();
        }
    }

    public static void threadSleep(){
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
