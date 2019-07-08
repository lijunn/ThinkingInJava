package com.thinking.in.java.chapter07;

import static com.thinking.in.java.PrintUtil.print;

public class FatherA {


    public void speak(){
        print("FatherA");
    }

    public final void run(){
        print("FatherA");
    }

}
