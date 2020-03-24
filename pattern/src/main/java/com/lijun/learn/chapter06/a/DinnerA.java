package com.lijun.learn.chapter06.a;

/**
 * 相同包下，权限访问
 */
public class DinnerA {

    public static void main(String[] args){
        Cookie cookie = new Cookie();

        //包访问权限
        cookie.packageMethod();

        //public 权限
        cookie.publicMethod();

        //private 权限,无法访问
//        cookie.privateMethod();

        //protected 权限
        cookie.protectedMethod();
    }
}
