package com.lijun.pattern.strategy.paystrategy;


/**
 * @author : LiJun
 * @date : 2020-04-02 11:54
 **/
public class UnionPay extends BasePayment {


    @Override
    protected double queryBalance(String uid) {
        return 200;
    }

    @Override
    public String getName() {
        return "银联支付";
    }
}
