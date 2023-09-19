package com.lijun.base.ajava.chapter06.a;


import static com.lijun.base.PrintUtil.print;

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
