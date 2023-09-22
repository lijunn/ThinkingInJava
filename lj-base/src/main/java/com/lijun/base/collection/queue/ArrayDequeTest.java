package com.lijun.base.collection.queue;

import java.util.ArrayDeque;

/**
 * @author : LiJun
 * @date : 2023-09-21 17:51
 * ArrayDeque 在 Java 中是一个双端队列（deque）的实现类，它可以在队列的两端进行高效的插入和删除操作
 *
 * 实现原理：
 *  1.数据结构：循环数组，使用 head、tail 指针表示队列的头尾
 *  2.动态扩容：每次扩容一倍，分别对 head 左右两边进行拷贝
 *
 * 时间复杂度：O(1)
 *
 * 应用：
 *  1.用于实现队列和栈
 *  2.缓存实现
 *  3.实现滑动窗口算法
 **/
public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);

        System.out.println(deque.toString());
    }


}
