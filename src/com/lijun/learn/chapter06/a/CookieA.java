package com.lijun.learn.chapter06.a;

/**
 * 相同包下的 继承
 */
public class CookieA extends Cookie {

    public static void main(String[] args){

        CookieA cookieA = new CookieA();

        //包访问权限
        cookieA.packageMethod();

        //public 权限
        cookieA.publicMethod();

        //private 权限,无法访问
//        cookieA.privateMethod();

        //protected 权限
        cookieA.protectedMethod();
    }
}
