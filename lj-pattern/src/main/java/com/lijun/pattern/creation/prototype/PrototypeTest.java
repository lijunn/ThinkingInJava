package com.lijun.pattern.creation.prototype;

import java.util.ArrayList;

/**
 * @author : LiJun
 * @date : 2020-04-02 17:28
 * 原型模式，和单例模式相反，就是为了方便克隆对象
 *
 * 浅克隆：如果被克隆的对象中有引用型变量，那么克隆的对象只会复制地址，不会重新创建该变量
 * 深克隆：引用型变量对象会被重新创建，地址会改变
 **/
public class PrototypeTest {

    public static void main(String[] args) throws CloneNotSupportedException {

        Person person = new Person();
        ArrayList<String> hobbies = new ArrayList<>();
        hobbies.add("游戏");
        hobbies.add("篮球");
        hobbies.add("爬山");
        person.setAge(18);
        person.setName("小米");
        person.setHobbies(hobbies);

        System.out.println("源对象：\n"+person.toString()+"\n");

        Person shallowInstance = (Person) person.getShallowCloneInstance();
        System.out.println("浅克隆对象：\n"+shallowInstance.toString());
        System.out.print("引用类型地址比较：");
        System.out.println(person.getHobbies() == shallowInstance.getHobbies());

        System.out.println();

        Person deepInstance = (Person) person.getDeepCloneInstance(person);
        System.out.println("深克隆对象：\n"+deepInstance.toString());
        System.out.print("引用类型地址比较：");
        System.out.println(person.getHobbies() == deepInstance.getHobbies());



    }
}
