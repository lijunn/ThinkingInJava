package com.lijun.base.pattern.structure.proxy.dynamicproxy;

/**
 * @author : LiJun
 * @date : 2020-04-01 10:22
 **/
public class Girl implements Person {
    @Override
    public void findLove() {
        System.out.println("高富帅...");
        System.out.println("身高180cm...");
        System.out.println("六块腹肌...");
    }

    @Override
    public int study(String book, int id) {
        System.out.println("学习："+book+"--"+id);
        return id;
    }
}
