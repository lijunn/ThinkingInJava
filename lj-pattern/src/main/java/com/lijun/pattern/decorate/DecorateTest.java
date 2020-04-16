package com.lijun.pattern.decorate;

/**
 * @author : LiJun
 * @date : 2020-04-10 15:02
 * 装饰器模式
 *
 * 优点：
 * 1、装饰者是继承的有力补充，比继承灵活，不改变原有对象的情况下动态地给一个对象 扩展功能，即插即用。
 * 2、通过使用不同装饰类以及这些装饰类的排列组合，可以实现不同效果。 3、装饰者完全遵守开闭原则。
 *
 * 缺点：
 * 1、会出现更多的代码，更多的类，增加程序复杂性。
 * 2、动态装饰时，多层装饰时会更复杂。
 **/
public class DecorateTest {
    public static void main(String[] args) {
        Pancakes pancakes = new Pancakes();

        printPancakes(pancakes);

        //加三个鸡蛋
        pancakes = new EggDecorate(pancakes);
        pancakes = new EggDecorate(pancakes);
        pancakes = new EggDecorate(pancakes);

        printPancakes(pancakes);

        //加个香肠
        pancakes = new SausageDecorate(pancakes);
        printPancakes(pancakes);

    }

    public static void printPancakes(Pancakes pancakes){
        System.out.println("\n煎饼套餐=============");
        System.out.println("套餐: "+pancakes.getName());
        System.out.println("价格: "+pancakes.getPrice());
    }
}
