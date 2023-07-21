package com.lijun.base.jvm;

/**
 * @author : LiJun
 * @date : 2023-07-12 11:22
 **/
public class ClassInfo {
    public static String static_str = "static_str_li_jun";
    public static int static_int = 100;

    private int age;

    public int incrAge(int num){
        String s1 = "FFFA";
        String s2 = new String("SSR");
        return age+num;
    }
}
