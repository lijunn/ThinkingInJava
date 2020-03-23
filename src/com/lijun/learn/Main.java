package com.lijun.learn;

public class Main {

    public static void main(String[] args){


        int a = 9;

        change(a);
        System.out.println("out a:"+a);
    }

    public static void change(int a){
        a = a+10;
        System.out.println("inner a:"+a);
    }

    static class Book{

    }

//    public static void f1(char x){
//        printnb("f1(char)");
//    }
    public static void f1(char x){
        printnb("f1(char)");
    }

    public static void printnb(String str){
        System.out.println(str+" ");
    }

    public static void f(int a,String b){
        printnb("a--b");
    }
    public static void f(String b,int a){
        printnb("b--a");
    }
}
