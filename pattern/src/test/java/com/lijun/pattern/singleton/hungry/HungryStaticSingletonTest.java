package com.lijun.pattern.singleton.hungry;



public class HungryStaticSingletonTest {

    public static void main(String[] args) {
        HungryStaticSingleton instance = HungryStaticSingleton.getInstance();
        System.out.println(instance);
    }

}
