package com.lijun.base.collection.queue;

import java.util.PriorityQueue;

/**
 * @author : LiJun
 * @date : 2023-09-22 15:41
 * PriorityQueue 是一种基于优先级的队列，它可以根据元素的优先级对元素进行排序，并在每次取出元素时返回优先级最高（或最低）的元素。
 *
 * 实现原理：
 *  1.数据结构：二叉堆（数组结构）
 *  2.可自定义比较器
 *
 * 应用：
 *  1.任务调度
 *  2.事件处理
 *  3.负载均衡
 *  5.最短路径
 **/
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.add(1);
        queue.add(5);
        queue.add(9);
        queue.add(0);
        queue.add(8);
        System.out.println(queue.toString());

        while (queue.size() > 0){
            System.out.println(queue.poll());
        }
    }
}
