package com.lijun.base.ajava;

/**
 * @author : LiJun
 * @date : 2020-05-26 09:47
 **/
public class StringTest {

    public static void main(String[] args) {

//        String str = "ABC";
//        String str2 = str.intern();
//        System.out.println(str == str2);

//        String s1 = new String("SSR");
//        s1.intern();
//        String s3 = "SSR";
//        System.out.println(s1 == s2);
//        System.out.println(s1 == s3);





//        String s3 = new String("1") + new String("1");
//        String s = "11";
//        String s = "11";
        "sss".intern();

        String s3 = new String("11");
        s3.intern();
        String s4 = "11";

        System.out.println(s4 == s3);

    }



}
