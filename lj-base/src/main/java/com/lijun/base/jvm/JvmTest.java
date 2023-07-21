package com.lijun.base.jvm;


/**
 * @author lijun
 */
public class JvmTest {

    private static final int _1MB = 1024 * 1024;

    /**
     * JVM参数：
     * -Xms20M -Xmx20M 设置堆内存最大最小值为 20mb
     * -Xmn10 设置Eden空间大小为10mb
     * -XX: SurvivorRatio=8 设置Eden:Survivor=8:1
     * -XX: +PrintGCDetails 打印GC信息
     *
     * @param args
     */
    public static void main(String[] args) {
//        byte[] allocation1, allocation2, allocation3, allocation4;
//        allocation1 = new byte[2 * _1MB];
//        allocation2 = new byte[2 * _1MB];
//        allocation3 = new byte[2 * _1MB];
//        allocation4 = new byte[4 * _1MB];
        ClassInfo classInfo = new ClassInfo();
    }

}
