package com.lijun.base.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : LiJun
 * @date : 2019-08-20 14:45
 * synchronized 使用对象的 wait,notify 方法实现等待唤醒机制，因为 synchronized 的实现原理就是对象锁
 *
 * Lock 使用的不是对象锁，所以无法使用 object的wait,notify方法
 * Lock 使用 Condition 来实现等待唤醒机制
 **/
public class ConditionTest {

    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        //测试 wait 和 notify
        new Thread(new Wait()).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(new Notify()).start();
    }



    public static class Wait implements Runnable{

        @Override
        public void run() {
            //获取对象锁
            lock.lock();

            try {
                //判断是否满足条件，不满足则等待
                if (flag){
                    System.out.println(Thread.currentThread()
                            +" flag is true. wait @ "+new SimpleDateFormat("HH:mm:ss").format(new Date()));

                    //调用 wait 方法
                    condition.await();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

            //条件满足,执行任务
            System.out.println("执行任务----");
        }
    }

    public static class Notify implements Runnable{

        @Override
        public void run() {
            //获取对象锁
            lock.lock();

            try {
                System.out.println(Thread.currentThread()
                        +" hold lock notify @ "+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                //修改条件
                flag = false;
                //通知
                condition.signalAll();

                TimeUnit.SECONDS.sleep(5);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

}
