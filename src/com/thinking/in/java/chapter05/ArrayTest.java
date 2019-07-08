package com.thinking.in.java.chapter05;

import static com.thinking.in.java.PrintUtil.print;

public class ArrayTest {
    public static void main(String[] args){


        InitialTest[] initial = {new InitialTest(),new InitialTest(),new InitialTest()};

        for (InitialTest i:initial){
            print(i.toString());
        }

    }
}
