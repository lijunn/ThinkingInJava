package com.lijun.base.ajava.chapter05;

import static com.lijun.base.PrintUtil.print;

public class InitialTest {

    public static void main(String[] args){

//        InitialValues initialValues = new InitialValues();
//        initialValues.printInitialValues();

        InitOrderTest initOrderTest = new InitOrderTest();

    }

    static class InitialValues{
        boolean t;
        char c;
        byte b;
        short s;
        int i;
        long l;
        float f;
        double d;
        InitialValues reference;


        public void printInitialValues(){
            print("DataType         InitialValues");
            print("boolean         "+t);
            print("char         "+c);
            print("byte         "+b);
            print("short         "+s);
            print("int         "+i);
            print("long       "+l);
            print("float         "+f);
            print("double         "+d);
            print("reference         "+reference);

        }
    }

    static class InitOrderTest{

        int a = 2;
        int b;

        public InitOrderTest(){
            print("a="+a);
            print("b="+b);
        }
    }

}
