package com.lijun.base.collection.set;

import java.util.TreeSet;

/**
 * @author : LiJun
 * @date : 2023-09-19 11:56
 * TreeSet 内部使用 HashMap 实现
 * 特点：可排序，可以范围查询
 **/
public class TreeSetTest {
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();

        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(4);
        treeSet.add(3);
        treeSet.add(5);
        treeSet.add(6);


        int key = 3,key1 = 2,key2 = 6;
        System.out.println("first-最小值:"+treeSet.first());
        System.out.println("last-最大值:"+treeSet.last());

        System.out.println("lower-小于key的第一个:"+treeSet.lower(key));
        System.out.println("higher-大于key的第一个:"+treeSet.higher(key));

        System.out.println("floor-小于或等于key的最大值:"+treeSet.floor(key));
        System.out.println("ceiling-大于或等于key的最小值:"+treeSet.ceiling(key));

        System.out.println("tailSet-大于等key:"+treeSet.tailSet(key));
        System.out.println("headSet-小于key:"+treeSet.headSet(key));

        System.out.println("subSet-大于等于key1，小于key2:"+treeSet.subSet(key1,key2));
    }
}
