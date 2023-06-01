package com.lijun.base.concurrent.thread;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : LiJun
 * @date : 2020-08-27 15:57
 **/
public class ThreadStatusTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
//                lock.lock();
                synchronized (ThreadStatusTest.class){
                    int count = 0;
                    System.out.println("t1："+ count++);

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

//                lock.unlock();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
//                lock.lock();
                synchronized (ThreadStatusTest.class) {
                    int count = 0;
                    System.out.println("t2：" + count++);

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                lock.unlock();
            }
        });

        t1.start();
        t2.start();

        while (true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程信息--1："+t1.toString());
            System.out.println("线程状态--1："+t1.getState().name());

            System.out.println("线程信息----2："+t2.toString());
            System.out.println("线程状态----2："+t2.getState().name());
        }
    }


}
