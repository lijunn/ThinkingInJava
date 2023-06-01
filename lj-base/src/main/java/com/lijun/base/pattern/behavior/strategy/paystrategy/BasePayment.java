package com.lijun.base.pattern.behavior.strategy.paystrategy;

import com.lijun.base.pattern.behavior.strategy.MsgResult;

/**
 * @author : LiJun
 * @date : 2020-04-02 11:42
 * 支付渠道
 **/
public abstract class BasePayment {

    /**
     * 查询余额
     * @param uid 用户id
     * @return
     */
    protected abstract double queryBalance(String uid);

    /**
     * 获取支付类型名称
     * @return
     */
    public abstract String getName();

    /**
     * 发起支付
     * @param uid 用户id
     * @param amount 订单金额
     * @return
     */
    public MsgResult pay(String uid,double amount){
        double balance = queryBalance(uid);
        if (balance < amount){
            return new MsgResult(500,"支付失败","余额不足");
        }else {
            return new MsgResult(200,"支付成功","支付金额:"+ balance);
        }
    }
}
