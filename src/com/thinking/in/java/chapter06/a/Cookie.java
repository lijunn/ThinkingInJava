package com.thinking.in.java.chapter06.a;


import static com.thinking.in.java.PrintUtil.print;

public class Cookie {

    void packageMethod(){
        print("packageMethod invoke");
    }

    public void publicMethod(){
        print("publicMethod invoke");
    }

    private void privateMethod(){
        print("privateMethod invoke");
    }

    protected void protectedMethod(){
        print("protectedMethod invoke");
    }
}
