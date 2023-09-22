package com.lijun.base.collection.map;

import java.util.TreeMap;

/**
 * @author : LiJun
 * @date : 2023-09-19 16:16
 * TreeMap 是 SortedMap 的实现，它基于红黑树数据结构来存储键值对。
 * TreeMap 是有序的，可以根据键的自然排序或自定义比较器进行排序
 *
 * 数据结构：红黑树
 *
 * 特点：
 * 1.排序方式：默认根据键的自然排序，构造函数中传入 Comparator 进行排序
 * 2.可范围查询
 *
 * 时间复杂度：O(logn)
 *
 * 应用：
 * 1.有序映射：TreeMap保持键的有序性，因此可以使用TreeMap作为有序映射来存储和管理数据。
 *  键的有序性可以用于按键查找、插入、删除等操作，并且支持获取最小键、最大键以及根据键的范围获取子映射等操作。
 *
 * 2.任务调度：TreeMap的键可以表示任务的时间戳或优先级
 *
 * 2.时间区间计算：TreeMap的键可以表示时间戳或时间区间，值可以表示与时间相关的数据。
 *      通过TreeMap的有序性，可以方便地进行时间区间的计算，例如查找与给定时间区间重叠的数据。
 *
 * 3.缓存实现：TreeMap可以用作缓存的实现，通过设置合适的键和值，可以实现基于键的范围查找、LRU（最近最少使用）缓存策略等。
 **/
public class TreeMapTest {

    public static void main(String[] args) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(11,11);
        treeMap.put(1,11);
        treeMap.put(2,12);
        treeMap.put(5,14);
        treeMap.put(9,16);

        System.out.println(treeMap.toString());
    }

}
