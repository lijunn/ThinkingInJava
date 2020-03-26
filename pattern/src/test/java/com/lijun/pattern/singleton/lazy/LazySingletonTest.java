package com.lijun.pattern.singleton.lazy;


import com.lijun.pattern.singleton.ExecutorThread;

public class LazySingletonTest {


    public static void main(String[] args) {
        Thread thread1 = new Thread(new ExecutorThread());
        Thread thread2= new Thread(new ExecutorThread());
        thread1.start();
        thread2.start();
    }

}
