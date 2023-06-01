package com.lijun.base.pattern.behavior.strategy;

import com.lijun.base.pattern.behavior.strategy.paystrategy.BasePayment;
import com.lijun.base.pattern.behavior.strategy.paystrategy.PayStrategy;

/**
 * @author : LiJun
 * @date : 2020-04-02 11:48
 **/
public class Order {

    /**用户id*/
    private String uid;
    /**订单id*/
    private String orderId;
    /**订单金额*/
    private Double amount;


    public MsgResult pay(String payKey){
        BasePayment payment = PayStrategy.getInstance().getPayment(payKey);

        System.out.println("开始支付...");
        System.out.println("您选择了:"+payment.getName()+" 的方式支付");
        MsgResult msgResult = payment.pay(uid, amount);
        System.out.println(msgResult.toString());

        return msgResult;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
