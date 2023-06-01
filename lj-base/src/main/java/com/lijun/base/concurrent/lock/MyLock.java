package com.lijun.base.concurrent.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author : LiJun
 * @date : 2020-08-01 13:42
 * 手动实现一个简易的锁
 * 1.使用 Unsafe 调用CAS方法，这样可以原子性的操作锁状态字段(同一时刻只有一个线程操作)，
 *      Unsafe 是JDK内部方法，我们调用会报异常，实现CAS也可以使用原子类 AtomicInteger
 *
 * 2.使用 CAS 去更新 status = 1 ，更新成功后该线程就获得了锁，其他线程再去更新时会失败，
 * 获取锁失败的线程递归调用，会一直尝试回去锁直到成功。
 **/
public class MyLock {

    /**
     * 锁状态字段
     */
    private volatile int status;

    /**
     * 定义一个 Unsafe ，用于使用 CAS
     * 因为 Unsafe.getUnsafe() 只供JDK内部使用，所以要通过反射获取Unsafe
     *
     * stateOffset 是标记字段，CAS时使用
     */
    private static final Unsafe unsafe = createUnsafe();
    public static Unsafe createUnsafe() {
        try {
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Field field = unsafeClass.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            return unsafe;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static final long stateOffset;
    static {
        try {
            stateOffset = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("status"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    /**
     * 使用CAS更新锁状态
     * @param expect
     * @param update
     * @return
     */
    protected final boolean compareAndSetState(int expect, int update) {
        // See below for intrinsics setup to support this
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    /**
     * 获取锁，获取不到会阻塞当前线程
     */
    public void lock(){
        if (compareAndSetState(0,1)){
            System.out.println(Thread.currentThread().getName()+":获取到锁");
        }else {
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName()+":尝试重新获取锁");
                lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放锁
     */
    public void unlock(){
        if (compareAndSetState(1,0)){
            System.out.println(Thread.currentThread().getName()+":释放锁");
        }
    }
}
