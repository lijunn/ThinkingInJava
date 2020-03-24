package com.lijun.learn.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author : LiJun
 * @date : 2019-08-17 13:24
 * 安全的终止线程
 **/
public class ThreadTest2 {


    public static void main(String[] args) throws InterruptedException {

//        RunnerA runnerA = new RunnerA();
//        Thread threadA = new Thread(runnerA);
//        threadA.start();
//        TimeUnit.SECONDS.sleep(1);
//        //使用 interrupt 信号终止线程
//        threadA.interrupt();
//
//        //非阻塞状态下调用 interrupt
//        RunnerB runnerB = new RunnerB();
//        Thread threadB = new Thread(runnerB);
//        threadB.start();
//        TimeUnit.SECONDS.sleep(1);
//        //使用共享变量 volatile 终止线程
//        runnerB.cancle();

        //阻塞状态下调用 interrupt
        RunnerC runnerC = new RunnerC();
        Thread threadC = new Thread(runnerC);
        threadC.start();
        TimeUnit.SECONDS.sleep(1);
        threadC.interrupt();

    }

    static class RunnerA implements Runnable{

        private int i;

        @Override
        public void run() {
            //根据中断状态来终止循环，从而终止线程
            while(!Thread.currentThread().isInterrupted() ){
                i++;
            }
            System.out.println("RunnerA-Count i = "+i);
        }
    }

    static class RunnerB implements Runnable{

        private int i;
        private volatile boolean on = true;

        @Override
        public void run() {
            //根据 volatile 变量来终止循环，从而终止线程
            while(on){
                i++;
            }
            System.out.println("RunnerB-Count i = "+i);
        }

        public void cancle(){
            on = false;
        }
    }

    static class RunnerC implements Runnable{

        private int i;

        @Override
        public void run() {

            try {
                Thread.sleep(10000);

                while(true){
                    i++;
                }

            } catch (InterruptedException e) {
                System.out.println("InterruptedException throw");
            }
            //发生异常后 interrupt 状态被重置为 false
            System.out.println("interrupt状态："+Thread.currentThread().isInterrupted());
            System.out.println("RunnerC-Count i = "+i);
        }

    }
}
