package com.lijun.learn.concurrent.thread;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author : LiJun
 * @date : 2019-08-13 15:17
 *
 * wait方法的使用必须在同步的范围内
 * wait()方法的作用是将当前运行的线程挂起（即让其进入阻塞状态），直到notify或notifyAll方法来唤醒线程.
 * wait(long timeout)，该方法与wait()方法类似，唯一的区别就是在指定时间内，如果没有notify或notifAll方法的唤醒，也会自动唤醒。
 *
 * void join():当前线程等该加入该线程后面，等待该线程终止。
 * void join(long millis):当前线程等待该线程终止的时间最长为 millis 毫秒。 如果在millis时间内，该线程没有执行完，那么当前线程进入就绪状态，重新等待cpu调度。
 * void join(long millis,int nanos):等待该线程终止的时间最长为 millis 毫秒 + nanos纳秒。如果在millis时间内，该线程没有执行完，那么当前线程进入就绪状态，重新等待cpu调度。
 *

 **/
public class ThreadTest {

    static Object lock = new Object();
    static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {


//        testWaitNotify();

        //测试 join
//        testJoin();

        testSleep();
    }


    public static void testWaitNotify() throws InterruptedException {
        //测试 wait 和 notify
        new Thread(new Wait()).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(new Notify()).start();
    }

    public static class Wait implements Runnable{

        @Override
        public void run() {
            //获取对象锁
            synchronized (lock){
                //判断是否满足条件，不满足则等待
                if (flag){
                    try {
                        System.out.println(Thread.currentThread()
                                +" flag is true. wait @ "+new SimpleDateFormat("HH:mm:ss").format(new Date()));

                        //调用 wait 方法，该线程会释放对象锁
                        lock.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //条件满足,执行任务
                System.out.println("执行任务----");
            }
        }
    }

    public static class Notify implements Runnable{

        @Override
        public void run() {
            //获取对象锁
            synchronized (lock){
                System.out.println(Thread.currentThread()
                        +" hold lock notify @ "+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                //修改条件
                flag = false;
                //通知
                lock.notifyAll();

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 测试 Join 方法
     */
    private static void testJoin(){

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    thread1.join();//先执行线程1 ，线程1执行完后在往下执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("线程2");
            }
        });

        thread2.start();
        thread1.start();
    }

    public static void testSleep(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1-开始运行");
                synchronized (lock){
                    System.out.println("线程1");
                    try {
                        lock.wait(5000);
                        System.out.println("线程1-继续执行");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2-开始运行");
                synchronized (lock){
//                    lock.notify();
                    System.out.println("线程2-唤醒");
                }
            }
        });
        thread1.start();
        thread2.start();

    }

}


