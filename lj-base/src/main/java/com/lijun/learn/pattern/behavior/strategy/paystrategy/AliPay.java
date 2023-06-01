package com.lijun.learn.pattern.behavior.strategy.paystrategy;


/**
 * @author : LiJun
 * @date : 2020-04-02 11:54
 **/
public class AliPay extends BasePayment {


    @Override
    protected double queryBalance(String uid) {
        return 300;
    }

    @Override
    public String getName() {
        return "支付宝支付";
    }
}
