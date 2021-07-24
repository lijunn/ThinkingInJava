package com.lijun.learn.innerClass;

import com.lijun.learn.innerClass.multiple.D;
import com.lijun.learn.innerClass.multiple.E;
import com.lijun.learn.innerClass.multiple.Z;

public class MainTest {


    public static void main(String[] args){
        Outer outer = new Outer();
        //调用外部类对象的方法创建内部类
        Outer.Inner inner = outer.getInner();
        inner.innerRun();

        //使用 .new 语法创建内部类对象
        Outer.Inner inner1 = outer.new Inner();
        inner1.innerRun();


        //内部类实现多继承, Z 可以同时满足 D 和 E 的类型
        Z z = new Z();
        testD(z);
        testE(z.makeE());
    }

    public static void testD(D d){
        d.runD();
    }

    public static void testE(E e){
        e.runE();
    }
}
