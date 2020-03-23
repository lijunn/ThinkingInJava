package com.lijun.learn.chapter06.b;

import com.lijun.learn.chapter06.a.Cookie;


/**
 * 不同包下的 继承
 */
public class CookieB extends Cookie {

    public static void main(String[] args){
        CookieB cookieB = new CookieB();

        //包访问权限 无法访问
//        cookieB.packageMethod();

        //public 权限
        cookieB.publicMethod();

        //private 权限,无法访问
//        cookieB.privateMethod();

        //protected 权限
        cookieB.protectedMethod();
    }
}
