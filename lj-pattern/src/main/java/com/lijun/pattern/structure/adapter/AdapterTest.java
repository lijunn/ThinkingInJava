package com.lijun.pattern.structure.adapter;

import com.lijun.pattern.structure.adapter.v1.ThirdLoginAdapter;

/**
 * @author : LiJun
 * @date : 2020-04-08 09:56
 * 适配器模式
 * 把一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作
 *
 * 适配器的工作原理：
 *
 *      1.转换模式
 *      原始对象 -> 适配器 -> 目标对象
 *      就是将原始对象给适配器，然后经过适配器适配后可以得到目标对象。
 *
 *      2.兼容模式
 *      目标对象 ->
 *                ---> 适配器
 *      原始对象 ->
 *
 *      有下面几种方法将原始对象注入适配器：
 *          1.适配器继承原始对象，则适配器就获得了原始对象
 *          2.组合模式，适配器持有
 *
 * 1.类型适配器：
 *      通过继承的方式实现适配
 *
 * 2.对象适配器
 *      通过组合关系实现适配，即适配器包含 A,B 两个对象，则适配器就可以完成 A,B 的功能
 *
 * 3.接口适配器
 *      为了在实现接口时避免实现所有的方法，可以先用一个抽象类当做适配器，先实现一些方法。
 *      然后在使用这个适配器时，就可以只实现需要的方法
 *
 * 优点：
 * 1、能提高类的透明性和复用，现有的类复用但不需要改变。
 * 2、目标类和适配器类解耦，提高程序的扩展性。
 * 3、在很多业务场景中符合开闭原则。
 *
 * 缺点：
 * 1、适配器编写过程需要全面考虑，可能会增加系统的复杂性。
 * 2、增加代码阅读难度，降低代码可读性，过多使用适配器会使系统代码变得凌乱。
 **/
public class AdapterTest {

    public static void main(String[] args) {

        //原有登陆服务有只有密码登陆
        LoginService loginService = new LoginService();
        loginService.register("xiaoma","1234");
        loginService.login("xiaoma","1234");

        //现在要添加一个第三方登陆的服务, ThirdLoginService , 如何才能兼容两种登陆服务
        //这里要使用到适配器模式
        ThirdLoginAdapter thirdLoginAdapter = new ThirdLoginAdapter();
        thirdLoginAdapter.loginForQQ("1");
        thirdLoginAdapter.login("xiaoma","1234");
    }
}
