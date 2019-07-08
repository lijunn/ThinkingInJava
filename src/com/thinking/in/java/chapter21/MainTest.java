package com.thinking.in.java.chapter21;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

/**
 * 死锁
 */

public class MainTest {

    public static Object resource1 = new Object();
    public static Object resource2 = new Object();

    public static void main(String[] args){


        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (resource1){
                    println("get resource1 :"+Thread.currentThread());


                    try {
//                        Thread.sleep(1000);//不会释放锁
                        wait();//会释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    println("waiting resource2 :"+Thread.currentThread());
                    synchronized (resource2){
                        println("get resource2 :"+Thread.currentThread());
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (resource2){
                    println("get resource2 :"+Thread.currentThread());


                    try {
//                        Thread.sleep(1000);
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    println("waiting resource1 :"+Thread.currentThread());
                    synchronized (resource1){
                        println("get resource1 :"+Thread.currentThread());
                    }
                }
            }
        }).start();

    }



}
