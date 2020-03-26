package com.lijun.pattern.singleton.register;


import com.lijun.pattern.singleton.ExecutorThread;

public class EnumSingletonTest {


    public static void main(String[] args) {
        Thread thread1 = new Thread(new ExecutorThread());
        Thread thread2= new Thread(new ExecutorThread());
        thread1.start();
        thread2.start();

    }
}