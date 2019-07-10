package com.thinking.in.java.jvm;

public class JvmTest {

    //方法区（jdk1.8 改为元空间）
    public static int staticVariable = 3;

    //对象存储在堆内存，其成员变量也存储在堆内存
    public int variable = 4;

    //方法的参数和局部变量都存储在线程私有的 jvm栈中
    public void method(){

        //jvm栈中
        int localVariable = 3;
        //object 的引用是在 jvm 栈中的，对象还是在堆内存
        TestObject object = new TestObject();

    }



    public static class TestObject{

    }

}
