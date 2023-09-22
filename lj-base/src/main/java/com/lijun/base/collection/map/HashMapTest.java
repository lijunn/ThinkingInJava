package com.lijun.base.collection.map;

import java.util.HashMap;

/**
 * @author : LiJun
 * @date : 2023-09-20 16:51
 *
 * 数据结构：Hash表、链表、红黑树
 *
 * 时间复杂度：平均时间复杂度为 O(1)
 **/
public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        hashMap.put("Tom", 29);
        hashMap.put("Jay", 22);
        hashMap.put("Jack", 10);
        hashMap.put("Mac", 11);
        hashMap.put("Json", 14);
        hashMap.put("Anni", 19);

        hashMap.forEach((key,value) -> {
            System.out.println(key+":"+value);
        });

    }
}
