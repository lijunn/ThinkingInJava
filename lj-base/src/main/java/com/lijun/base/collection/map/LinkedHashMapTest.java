package com.lijun.base.collection.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : LiJun
 * @date : 2023-09-20 16:51
 * LinkedHashMap是HashMap的一个具体实现，它通过使用链表维护元素的插入顺序，除了具备HashMap的快速查找特性外，还可以保持元素插入的顺序
 *
 * 数据结构：在HashMap的基础上加了双向链表用于记录顺序
 *
 * 特点：
 * 1.当 accessOrder=true 时，最近被访问的元素会排到最后面
 * 2.当 accessOrder=false 时，最近被插入的元素会排到最后面
 * 3.removeEldestEntry 方法返回 true 时会删除最前面的元素
 *
 * 时间复杂度：平均为 O(1)
 *
 * 应用：
 * 1.LRU 缓存策略
 * 2.FIFO 缓存策略
 * 3.实现队列和栈（没有ArrayDeque高效）
 **/
public class LinkedHashMapTest {
    public static void main(String[] args) {
        lurCacheTest();

        fifoCacheTest();
    }

    public static void lurCacheTest(){
        System.out.println();
        System.out.println("============lurCacheTest start===========");
        System.out.println();

        LurCacheMap<String, Integer> cacheMap = new LurCacheMap<>(3);
        cacheMap.put("Tom", 29);
        cacheMap.put("Jay", 22);
        cacheMap.put("Jack", 10);
        System.out.println(cacheMap.toString());

        cacheMap.put("Mac", 11);
        System.out.println("添加Mac:"+cacheMap.toString());

        cacheMap.get("Jay");
        System.out.println("访问Jay:"+cacheMap.toString());

        cacheMap.put("Anni", 19);
        System.out.println("添加Anni:"+cacheMap.toString());
    }

    public static void fifoCacheTest(){
        System.out.println();
        System.out.println("============fifoCacheTest start===========");
        System.out.println();

        FifoCacheMap<String, Integer> cacheMap = new FifoCacheMap<>(3);
        cacheMap.put("Tom", 29);
        cacheMap.put("Jay", 22);
        cacheMap.put("Jack", 10);
        System.out.println(cacheMap.toString());

        cacheMap.put("Mac", 11);
        System.out.println("添加Mac:"+ cacheMap.toString());

        cacheMap.put("Json", 14);
        System.out.println("添加Json:"+cacheMap.toString());
    }

    /**
     * 实现最近最少使用缓存策略
     */
    public static class LurCacheMap<K,V>  extends LinkedHashMap<K,V> {
        private final int cacheSize;

        public LurCacheMap(int cacheSize) {
            super(cacheSize,0.75f,true);
            this.cacheSize = cacheSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > cacheSize;
        }
    }

    /**
     * 实现固定大小的 FIFO 缓存策略
     */
    public static class FifoCacheMap<K,V>  extends LinkedHashMap<K,V> {
        private final int cacheSize;

        public FifoCacheMap(int cacheSize) {
            super(cacheSize,0.75f,false);
            this.cacheSize = cacheSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > cacheSize;
        }
    }
}
