package com.lijun.base.pattern.behavior.strategy;

import com.lijun.base.pattern.behavior.strategy.paystrategy.PayStrategy;

/**
 * @author : LiJun
 * @date : 2020-04-02 11:42
 *
 * 策略模式（Strategy Pattern）
 *  是指定义了算法家族、分别封装起来，让它们之间可以互相替换，此模式让算法的变化不会影响到使用算法的用户。
 *
 *  不同策略直接是平级的，可以相互替换的
 *
 *  优点：
 *  1、策略模式符合开闭原则。
 *  2、避免使用多重条件转移语句，如 if...else...语句、switch 语句
 *  3、使用策略模式可以提高算法的保密性和安全性。
 *
 *  缺点：
 *  1、客户端必须知道所有的策略，并且自行决定使用哪一个策略类。
 *  2、代码中会产生非常多策略类，增加维护难度。
 *
 **/
public class StrategyTest {

    public static void main(String[] args) {
        //创建订单
        Order order = new Order();
        order.setAmount(450.0);
        order.setUid("1");
        order.setOrderId("20200402110");

        //开始支付
        order.pay(PayStrategy.JD_PAY);
//        order.pay(PayStrategy.ALI_PAY);

    }
}
