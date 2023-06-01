package com.lijun.base.concurrent.container;


import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.*;

/**
 * @author : LiJun
 * @date : 2019-10-21 16:10
 **/
public class ArrayBlockingQueueTest {

    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS, new SynchronousQueue<>());

    /**
     * Queue api
     */
    @Test
    public void QueueApi() {
        BlockingQueue queue = new ArrayBlockingQueue<Integer>(5);

        queue.add(0);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);

        try {
            //使用 add 添加元素超出容量，抛出异常
            queue.add(5);
        }catch (Exception e){
            e.printStackTrace();
        }

        //使用 offer 添加元素超出容量，则返回 false 表示添加失败
        System.out.println(queue.offer(5));
        System.out.println(queue);

        //获取并移除第一个元素
        System.out.println(queue.remove());
        System.out.println(queue.poll());

        System.out.println(queue);

        //获取第一个元素，不移除
        System.out.println(queue.element());
        System.out.println(queue.peek());

    }

    @Test
    public void dequeTest(){
        Deque<Integer> deque = new ArrayDeque<>();
        System.out.println(deque);
        deque.add(1);
        deque.add(2);
        deque.add(3);

        deque.addFirst(4);
        deque.addFirst(5);
        System.out.println(deque);
    }

    @Test
    public void blockingQueueTest(){


        BlockingQueue blockingQueue = new ArrayBlockingQueue(5);


        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i<8;i++){
                    try {
                        Thread.sleep(3000);
                        blockingQueue.take();
                        System.out.println("消费者-消费--："+blockingQueue);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        for (int i = 0;i<8;i++){
            try {
                Thread.sleep(1000);
                blockingQueue.put(i);
                System.out.println("生产者-生产--："+blockingQueue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //不让主线程结束，以便打印日志
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void synchronousQueueTest(){
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();

        threadPool.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("消费者---"+synchronousQueue.take());
                    System.out.println("消费者---"+synchronousQueue);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                boolean offer = synchronousQueue.offer(1);
                System.out.println("生产者+++"+offer);
                System.out.println("生产者+++"+synchronousQueue);
            }
        });



        //不让主线程结束，以便打印日志
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tast(){
        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY   = (1 << COUNT_BITS) - 1;
        int CAPACITY2   = ~CAPACITY;

        int RUNNING    = -1 << COUNT_BITS;
        int SHUTDOWN   =  0 << COUNT_BITS;
        int STOP       =  1 << COUNT_BITS;
        int TIDYING    =  2 << COUNT_BITS;
        int TERMINATED =  3 << COUNT_BITS;

        System.out.println(COUNT_BITS+"=COUNT_BITS="+ Integer.toBinaryString(COUNT_BITS));
        System.out.println(CAPACITY+"=CAPACITY="+ Integer.toBinaryString(CAPACITY));
        System.out.println(CAPACITY2+"=CAPACITY2="+ Integer.toBinaryString(CAPACITY2));


        System.out.println(RUNNING+"=RUNNING="+ Integer.toBinaryString(RUNNING));
        System.out.println(SHUTDOWN+"=SHUTDOWN="+ Integer.toBinaryString(SHUTDOWN));
        System.out.println(STOP+"=STOP="+ Integer.toBinaryString(STOP));
        System.out.println(TIDYING+"=TIDYING="+ Integer.toBinaryString(TIDYING));
        System.out.println(TERMINATED+"=TERMINATED="+ Integer.toBinaryString(TERMINATED));

        int s = RUNNING & CAPACITY;
        int s2 = STOP & CAPACITY;
        System.out.println(s+"==="+Integer.toBinaryString(s));
        System.out.println(s2+"==="+Integer.toBinaryString(s2));


    }
}
