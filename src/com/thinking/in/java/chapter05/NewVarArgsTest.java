package com.thinking.in.java.chapter05;

import static com.thinking.in.java.PrintUtil.print;


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
