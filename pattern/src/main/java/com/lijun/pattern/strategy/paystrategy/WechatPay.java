package com.lijun.pattern.strategy.paystrategy;


/**
 * @author : LiJun
 * @date : 2020-04-02 11:54
 **/
public class WechatPay extends BasePayment {


    @Override
    protected double queryBalance(String uid) {
        return 500;
    }

    @Override
    public String getName() {
        return "微信支付";
    }
}
