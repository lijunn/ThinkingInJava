package com.lijun.base.ajava.chapter06.b;

import com.lijun.base.ajava.chapter06.a.Cookie;

/**
 * 不同包下，权限访问
 */
public class DinnerB {

    public static void main(String[] args){


        Cookie cookie = new Cookie();


        //包访问权限 无法访问
//        cookie.packageMethod();

        //public 权限
        cookie.publicMethod();

        //private 权限,无法访问
//        cookie.privateMethod();

        //protected 权限（也具有包访问权限的特性） 无法访问，但是如果有继承关系则可以跨包访问
//        cookie.protectedMethod();
    }
}
