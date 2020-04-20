package com.lijun.learn.regx;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author : LiJun
 * @date : 2020-04-19 12:28
 **/
public class regxTest {

    public static void main(String[] args) {
        String[] strings = {"f","s"};
        String s = Arrays.toString(strings).replaceAll("\\[|\\]", " ");
        System.out.println(s);

        Integer integer = Integer.valueOf(" 1 ");
        System.out.println(integer);
    }
}
