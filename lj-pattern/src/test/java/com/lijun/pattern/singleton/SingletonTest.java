package com.lijun.pattern.singleton;


public class SingletonTest {


    public static void main(String[] args) {
        Thread thread1 = new Thread(new ExecutorThread());
        Thread thread2= new Thread(new ExecutorThread());
        thread1.start();
        thread2.start();

    }
}