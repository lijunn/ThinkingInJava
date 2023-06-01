package com.lijun.base.pattern.behavior.delegate;

/**
 * @author : LiJun
 * @date : 2020-04-02 09:55
 *
 * 委托注重结果 : client -> delegate -> objA,objB,...
 *              委托更多的是对任务的调度分发，客户端只关心结果
 *
 * 代理注重过程 : client -> proxy -> objA
 *              代理注重调用过程中对被代理对象的增强
 *
 *
 * 客户请求（Boss）、委派者（Leader）、被被委派者（Target）
 * 委派者要持有被委派者的引用
 *
 * 代理模式注重的是过程， 委派模式注重的是结果
 * 策略模式注重是可扩展（外部扩展），
 *
 * 委派模式注重内部的灵活和复用
 * 委派的核心：就是分发、调度、派遣
 * 委派模式：就是静态代理和策略模式一种特殊的组合
 **/
public class DelegateTest {

    public static void main(String[] args) {
        Leader leader = Leader.getInstance();
        Boss boss = new Boss();

        boss.doWork("加密", leader);

        System.out.println("\n");

        boss.doWork("登陆", leader);
    }

}
