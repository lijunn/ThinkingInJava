package com.lijun.learn.datastructure;

/**
 * @author : LiJun
 * @date : 2019-07-30 17:23
 **/
public class Main {

    public static void main(String[] args){


//        ArrayList<String> strings = new ArrayList<>();

        MyArrayList<String> strings = new MyArrayList<>();

        strings.add("A");
        strings.add("B");
        strings.add("C");

        System.out.println(strings.toString());

        String s2 = strings.get(2);
        int c = strings.indexOf("C");

        System.out.println("s2="+s2 +"  "+c);

        strings.remove(2);
        strings.remove("C");


        System.out.println(strings.toString());

    }
}
