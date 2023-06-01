package com.lijun.base.concurrent.lock;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

/**
 * @author : LiJun
 * @date : 2020-08-01 13:42
 **/
public class MyLockTest {
    private static int count;
    public static void main(String[] args) {
        MyLock myLock = new MyLock();

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        for(int i=0;i<20;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    myLock.lock();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;

                    myLock.unlock();
                }
            });
        }

        while (executorService.getCompletedTaskCount() != 20){

        }
        //打印出线程池信息，检查线程是否执行完了
        println(executorService.toString());
        //volatileCount 和 count 的值有可能小于 1000
        println("count= "+count);
    }
}
