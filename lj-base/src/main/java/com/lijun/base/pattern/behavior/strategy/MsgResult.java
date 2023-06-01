package com.lijun.base.pattern.behavior.strategy;

/**
 * @author : LiJun
 * @date : 2020-04-02 11:42
 * 支付后的结果
 **/
public class MsgResult {

    private int code;

    private String msg;

    private Object data;

    public MsgResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "MsgResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
