package com.lijun.base.ajava.chapter05;

import static com.lijun.base.PrintUtil.print;


public class NewVarArgsTest {

    public static void main(String[] args){

        printArray("s","s",3,4,5);
        printArray();
    }

    public static void printArray(Object... args){




        for (Object object:args){
            print(object.toString());
        }

    }
}
