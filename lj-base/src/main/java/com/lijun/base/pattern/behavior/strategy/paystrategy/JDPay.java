package com.lijun.base.pattern.behavior.strategy.paystrategy;


/**
 * @author : LiJun
 * @date : 2020-04-02 11:54
 **/
public class JDPay extends BasePayment {


    @Override
    protected double queryBalance(String uid) {
        return 500;
    }

    @Override
    public String getName() {
        return "京东白条支付";
    }
}
