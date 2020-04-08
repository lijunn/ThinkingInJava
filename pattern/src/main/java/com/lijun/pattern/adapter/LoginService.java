package com.lijun.pattern.adapter;

/**
 * @author : LiJun
 * @date : 2020-04-08 10:02
 * 原有的登陆服务
 **/
public class LoginService {

    /**
     * 登陆方法
     * @param userName 用户名
     * @param password 密码
     */
    public ResultMsg login(String userName, String password){
        return new ResultMsg(200,"登陆成功","账号密码登陆");
    }

    /**
     * 注册
     * @param userName 用户名
     * @param password 密码
     */
    public ResultMsg register(String userName,String password){
        return new ResultMsg(200,"注册成功","");
    }
}
