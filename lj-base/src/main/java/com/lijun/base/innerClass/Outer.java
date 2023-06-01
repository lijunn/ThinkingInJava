package com.lijun.base.innerClass;

import static com.lijun.base.PrintUtil.print;

public class Outer {

    String outerStr1 = "str1";
    public String outerStr2 = "str2";
    private String outerStr3 = "str3";


    public void outerRun(){
        //外部类无法访问内部类
//        print(""+innerStr1);
//        print(""+innerStr2);
//        print(""+innerStr3);

    }


    public Inner getInner(){
        return new Inner();
    }

    //非静态内部类，只能在外部类里面创建
    class Inner{
        String innerStr1;
        public String innerStr2;
        private String innerStr3;

        public void innerRun(){
            //内部类可以访问外部类成员
            print(""+outerStr1);
            print(""+outerStr2);
            print(""+outerStr3);
        }
    }
}
