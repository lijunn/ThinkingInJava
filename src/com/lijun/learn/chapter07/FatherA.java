package com.lijun.learn.chapter07;

import static com.lijun.learn.PrintUtil.print;

public class FatherA {


    public void speak(){
        print("FatherA");
    }

    public final void run(){
        print("FatherA");
    }

}
