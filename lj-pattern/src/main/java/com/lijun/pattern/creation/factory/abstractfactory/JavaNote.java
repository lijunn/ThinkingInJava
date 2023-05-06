package com.lijun.pattern.creation.factory.abstractfactory;

/**
 * @author : LiJun
 * @date : 2020-03-23 17:39
 **/
public class JavaNote implements INote {
    @Override
    public void note() {
        System.out.println("创建java笔记");
    }
}
