package com.lijun.base.enumtest;

/**
 * @author : LiJun
 * @date : 2020-03-24 11:33
 **/
public class EnumMethodDemo {


    public static void main(String[] args) {
        Color blue = Color.BLUE;
        Color green = Color.green;

        System.out.println(blue.name());
        System.out.println(blue.getDeclaringClass());
        System.out.println(blue.getClass());
        System.out.println(blue.hashCode());

        System.out.println(green.name());
        System.out.println(green.hashCode());

    }
}
