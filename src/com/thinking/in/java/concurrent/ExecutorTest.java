package com.thinking.in.java.concurrent;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;


/**
 *
 * CachedThreadPool : 该方法返回一个可根据实际情况调整线程数量的线程池，会优先复用空闲的线程
 *
 * FixedThreadPool : 固定线程数量的线程池，当有新的任务提交时，如果有空闲线程则立即执行，否则新任务会被暂存到一个任务队列中
 *
 * SingleThreadExecutor ： 只有一个线程的线程池，如果多余的任务被提交则会暂存在一个任务队列中
 *
 *
 *
 */
public class ExecutorTest {

    public static void main(String[] args){

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        /**
         * 以上三种创建线程池的方式，底层都是通过 new ThreadPoolExecutor() 方法创建的
         * Executors 返回线程池对象的弊端如下：
         *
         * FixedThreadPool 和 SingleThreadExecutor ： 允许请求的队列长度为 Integer.MAX_VALUE ，可能堆积大量的请求，从而导致OOM。
         * CachedThreadPool 和 ScheduledThreadPool ： 允许创建的线程数量为 Integer.MAX_VALUE ，可能会创建大量线程，从而导致OOM。
         */


        /**
         * param1 核心线程数：当前线程池最少存在的线程数，即使线程处于空闲状态
         * param2 最大线程数：允许存在的最大线程数
         * param3 空闲线程存活时间：当超出核心线程数的线程处于空闲状态时，可存活的时间（即在核心线程数外的空闲线程在指定的时间内会被回收）
         * param4 时间单位
         * param5 任务队列：存储 execute 方法提交的 Runnable 任务
         * param6 线程创建工厂：用于创建线程
         * param7 拒绝执行的回调：当线程池满了或者任务队列满了，会回调这个方法以通知调用者当前线程池已无法执行更多任务了
         *        ThreadPoolExecutor的默认实现出现这种情况时会抛出 RejectedExecutionException异常
         */
        ThreadPoolExecutor myThreadPool = new ThreadPoolExecutor(
                2,
                4,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new MyThreadFactory());


        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) myThreadPool;
//        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) cachedThreadPool;
//        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) fixedThreadPool;
//        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) singleThreadPool;

        println("startBefore==="+threadPoolExecutor.toString());

        for (int i=0;i<10;i++){

            threadPoolExecutor.execute(new Task());
        }

        while (true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            println("startAfter==="+threadPoolExecutor.toString());
        }

    }


    public static class Task implements Runnable{

        @Override
        public void run() {
            println("Thread name: "+Thread.currentThread().getName());

            try {
                Random random = new Random();
                int second = random.nextInt(10);

                Thread.sleep(second*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            for (int i=0;i<1000;i++){
//                Random random = new Random();
//                Integer d = Math.abs(random.nextInt());
//            }
        }
    }

    /**
     * 自定义线程工厂，这样可以自定义线程的名称
     */
    public static class MyThreadFactory implements ThreadFactory{
        private String namePrefix;
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        private ThreadGroup group;

        public MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();

            namePrefix = "lj-poolNum-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()){
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY){
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }

    }

}
