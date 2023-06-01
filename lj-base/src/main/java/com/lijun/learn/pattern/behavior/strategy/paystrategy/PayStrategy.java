package com.lijun.learn.pattern.behavior.strategy.paystrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : LiJun
 * @date : 2020-04-02 11:55
 **/
public class PayStrategy {
    /**支付宝支付*/
    public static final String ALI_PAY = "ali_pay";
    /**京东白条支付*/
    public static final String JD_PAY = "JD_pay";
    /**微信支付*/
    public static final String WECHAT_PAY = "wechat_pay";
    /**银联支付*/
    public static final String UNION_PAY = "union_pay";
    /**默认支付*/
    public static final String DEFAULT_PAY = ALI_PAY;

    private static Map<String, BasePayment> paymentMap = new ConcurrentHashMap<>();
    private static volatile PayStrategy instance;

    static {
        paymentMap.put(ALI_PAY,new AliPay());
        paymentMap.put(JD_PAY,new JDPay());
        paymentMap.put(WECHAT_PAY,new WechatPay());
        paymentMap.put(UNION_PAY,new UnionPay());
    }

    private PayStrategy() {}

    public static PayStrategy getInstance(){
        if (null == instance){
            synchronized (PayStrategy.class){
                if (null == instance){
                    instance = new PayStrategy();
                }
            }
        }
        return instance;
    }


    public BasePayment getPayment(String payKey){
        if (paymentMap.containsKey(payKey)){
            return paymentMap.get(payKey);
        }

        return paymentMap.get(DEFAULT_PAY);
    }
}
