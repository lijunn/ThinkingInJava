package com.thinking.in.java.chapter21;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : LiJun
 * @date : 2019-08-14 17:00
 * 读写锁 ReentrantReadWriteLock
 * 特点：
 * 1.有两把锁，读锁和写锁
 * 2.读锁是共享式的：可以多个线程同时进行读 ，写锁是独占式的：只能有一个线程写入，二且当获得写锁后所有的读操作都被阻塞，直到写锁被释放
 *
 * 下例是 Cache 组合一个非线程安全的HashMap 作为缓存的实现，同时使用读锁和写锁保证线程安全。
 * 在getValue(String key)方法中，需要获取读锁，因为读锁是共享锁，因此在并发访问下不会被阻塞，可以多个线程同时访问资源。
 * 写操作 putValue(String key,int value) 在更新HashMap 时必须先获取写锁，当获取写锁后，其他线程对于读锁和写锁的获取都将被阻塞，
 * 而只有写锁被释放后其他读写操作才能继续。Cache 使用读写锁提升度操作的并发性，也保证了每次写操作对所有读写操作的可见性，同时简化了编程方式
 *
 **/
public class ReentrantReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {
        Cache cache = new Cache();


        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (;;){
                    System.out.println("线程1-读-"+cache.getValue("A"));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (;;){
                    System.out.println("线程2-读-"+cache.getValue("A"));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread.sleep(1000);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                cache.putValue("E",5);
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                cache.putValue("F",6);
            }
        });

    }


    public static class Cache{

        private HashMap<String,Integer> cacheMap = new HashMap<>(30);

        {
            //添加一些数据
            cacheMap.put("A",1);
            cacheMap.put("B",2);
            cacheMap.put("C",3);
            cacheMap.put("D",4);
        }

        private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        private Lock readLock = readWriteLock.readLock();
        private Lock writeLock = readWriteLock.writeLock();


        public int getValue(String key){
            readLock.lock();

            try {
                return cacheMap.get(key);
            }finally {
                readLock.unlock();
            }
        }

        public void putValue(String key,int value){
            System.out.println("获取写锁---*****");
            writeLock.lock();

            try {
                System.out.println("写入数据 "+key+":"+value);
                cacheMap.put(key,value);

                //让写入的时间变长，看看读的线程会不会被阻塞
                for (int i=0;i<10;i++){
                    System.out.println("当前正在写数据------"+i);
                    Thread.sleep(1000);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                writeLock.unlock();
                System.out.println("释放写锁---*****");
            }
        }
    }

}
