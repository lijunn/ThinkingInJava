package com.lijun.base.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @author : LiJun
 * @date : 2020-05-25 16:03
 * java中四种引用：
 * 1.强引用(StrongReference)：GC不会回收还存在强引用的对象
 * 2.软引用(SoftReference)：GC在内存不足时会回收
 * 3.弱引用(WeakReference)：GC在下一次清理时会回收，无论当前内存是否足够
 * 4.虚引用(PhantomReference)：虚引用的存在不会对对象的生命周期产生影响，也无法通过虚引用获取对象实例
 **/
public class ReferenceTest {

    public static void main(String[] args) throws InterruptedException {
        //强引用
        PP ppStrongReference = new PP("strong pp");

        //软引用
        SoftReference<PP> ppSoftReference = new SoftReference<>(new PP("soft pp"));

        //弱引用
        WeakReference<PP> ppWeakReference = new WeakReference<>(new PP("weak pp"));

        //虚引用
        PhantomReference<PP> ppPhantomReference = new PhantomReference<>(new PP("soft pp"), null);

        printTotalMemory();


        myPrint(ppStrongReference, ppSoftReference, ppWeakReference, ppPhantomReference);

        //触发垃圾回收
        System.gc();

        myPrint(ppStrongReference,ppSoftReference,ppWeakReference,ppPhantomReference);

        //模拟内存不足时触发GC
        ArrayList<Object> list = new ArrayList<>();
        for (;;){
            //当对象只存在软引用，还是被回收时则说明内存不足触发了GC



            if (Runtime.getRuntime().freeMemory()<1800000){
                System.gc();
                System.out.println("软引用："+ppSoftReference.get());
            }

            printFreeMemory();
            list.add(new Object());
        }

//        myPrint(ppStrongReference,ppSoftReference,ppWeakReference,ppPhantomReference);
//
//        for (;;);
    }

    private static void myPrint(PP ppStrongReference, SoftReference<PP> ppSoftReference, WeakReference<PP> ppWeakReference, PhantomReference<PP> ppPhantomReference) {
        System.out.println("ppStrongReference:" + ppStrongReference);
        System.out.println("ppSoftReference:" + ppSoftReference.get());
        System.out.println("ppWeakReference:" + ppWeakReference.get());
        System.out.println("ppPhantomReference:" + ppPhantomReference.get());
        System.out.println("================");
    }


    private static void printFreeMemory(){
        Runtime runtime = Runtime.getRuntime();
        System.out.println("可用内存(M):"+runtime.freeMemory());
    }

    private static void printTotalMemory(){
        Runtime runtime = Runtime.getRuntime();
        System.out.println("最大内存(M):"+runtime.maxMemory());
        System.out.println("总内存(M):"+runtime.totalMemory());
    }


    static class PP{
        String name;

        public PP(String name) {
            this.name = name;
        }
    }
}
