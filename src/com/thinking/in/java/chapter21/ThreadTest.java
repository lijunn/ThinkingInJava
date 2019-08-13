package com.thinking.in.java.chapter21;


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

    public static void main(String[] args) throws InterruptedException {

        //测试 wait 和 notify
        WaitTest waitTest = new WaitTest();
        waitTest.testWait();

        //测试 join
//        testJoin();

    }

    public static class WaitTest{

        public void testWait() throws InterruptedException {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程-start");

                    startWait();

                    System.out.println("线程-end");
                }
            });
            thread.start();
            System.out.println("主线程");

            Thread.sleep(2000);

            //唤醒线程时必须加锁，而且是和 wait 加的是同一对象的锁
            synchronized (this){
                notify();
            }
        }

        private void startWait(){
            System.out.println("Start-----wait");
            //wait 时必须加锁
            synchronized (this){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("End-------wait");
        }
    }



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
}
