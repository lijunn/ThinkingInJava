package com.lijun.pattern.factory.abstractfactory;

/**
 * @author : LiJun
 * @date : 2020-03-23 17:39
 **/
public class PythonNote implements INote {
    @Override
    public void note() {
        System.out.println("创建python笔记");
    }
}
