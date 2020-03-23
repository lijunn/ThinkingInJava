package com.lijun.learn.chapter05;

import static com.lijun.learn.PrintUtil.print;

public class ArrayTest {
    public static void main(String[] args){


        InitialTest[] initial = {new InitialTest(),new InitialTest(),new InitialTest()};

        for (InitialTest i:initial){
            print(i.toString());
        }

    }
}
