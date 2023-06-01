package com.lijun.base.datastructure;

import java.util.AbstractList;
import java.util.Arrays;

/**
 * @author : LiJun
 * @date : 2019-07-30 17:15
 **/
public class MyArrayList<E> extends AbstractList{

    private Object[] array;

    /**初始容量*/
    private int capacity = 10;
    /**当前大小*/
    private int size;


    public MyArrayList() {
        array = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        return (E) array[index];
    }


    @Override
    public boolean add(Object element) {
        add(size,element);
        return true;
    }

    @Override
    public void add(int index, Object element) {
        if (index > size || index <0){
            throw new IndexOutOfBoundsException();
        }

        if (size <= capacity){
            array[index] = element;
        }else {
            //容量不足扩容
            capacity = capacity * 2;
            array = Arrays.copyOf(array, capacity);
        }
        size++;
    }

    @Override
    public Object remove(int index) {
        if (index >= size || index <0){
            throw new IndexOutOfBoundsException();
        }


        return array[index] = null;
    }




}
