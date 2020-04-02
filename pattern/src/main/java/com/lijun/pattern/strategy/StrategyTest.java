package com.lijun.pattern.strategy;

import com.lijun.pattern.strategy.paystrategy.PayStrategy;

/**
 * @author : LiJun
 * @date : 2020-04-02 11:42
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
