package com.lijun.base.collection.list;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author : LiJun
 * @date : 2023-09-21 15:33
 * ArrayList 是一个动态数组的实现，使用 System.copyArray 方法实现动态扩容，
 * System.copyArray 是由jvm实现的，利用整块的内存拷贝提高拷贝的效率
 *
 * 1.扩容：新建一个原数组1.5倍大小的数组，使用 System.copyArray 拷贝到新数组
 * 2.删除：删除指定位置的数据,使用 System.copyArray 在当前数组拷贝
 * 3.使用 modCount 记录结构修改的次数：
 *      modCount 的主要作用是用于实现快速失败机制（fail-fast）。
 *      当通过迭代器遍历 ArrayList 或使用迭代器对其进行修改操作时，会比较迭代器创建时的 modCount 值和当前 ArrayList 的 modCount 值，
 *      如果两者不相等，就会抛出 ConcurrentModificationException 异常
 **/
public class ArrayListTest {
    public static void main(String[] args) throws  IllegalAccessException, NoSuchFieldException {
        ArrayList<Integer> list = new ArrayList<>(5);

        for (int i = 0; i <15; i++) {
            list.add(i);

            // 获取 ArrayList 内部数组的长度
            Field elementDataField = ArrayList.class.getDeclaredField("elementData");
            elementDataField.setAccessible(true);
            Object[] elementData = (Object[]) elementDataField.get(list);
            int arrayLength = elementData.length;
            System.out.println("ArrayList 内部数组的长度为: " + arrayLength);
        }
    }
}
