package com.lijun.pattern.structure.proxy.staticproxy;

/**
 * @author : LiJun
 * @date : 2020-04-01 10:05
 **/
public class StaticProxyTest {

    public static void main(String[] args) {
        Father father = new Father(new Son());
        father.findLove();
    }
}
