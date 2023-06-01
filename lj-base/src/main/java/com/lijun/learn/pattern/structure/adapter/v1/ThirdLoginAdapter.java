package com.lijun.learn.pattern.structure.adapter.v1;

import com.lijun.learn.pattern.structure.adapter.LoginService;
import com.lijun.learn.pattern.structure.adapter.ResultMsg;
import com.lijun.learn.pattern.structure.adapter.ThirdLoginService;

/**
 * @author : LiJun
 * @date : 2020-04-08 10:17
 * 简单版本的适配器：
 * 继承 LoginService 并且实现 ThirdLoginService
 * 这样 ThirdLoginAdapter 就兼容了两种登陆服务，
 * 既可以使用新的登陆方法，也可以使用老的登陆方法
 *
 **/
public class ThirdLoginAdapter extends LoginService implements ThirdLoginService {

    @Override
    public ResultMsg loginForQQ(String id) {
        return new ResultMsg(200,"登陆成功","qq登陆");
    }

    @Override
    public ResultMsg loginForWeChat(String id) {
        return new ResultMsg(200,"登陆成功","微信登陆");
    }

    @Override
    public ResultMsg loginForToken(String token) {
        return new ResultMsg(200,"登陆成功","账token登陆");
    }

    @Override
    public ResultMsg loginForTelephone(String telephone, String code) {
        return new ResultMsg(200,"登陆成功","手机验证码登陆");
    }

    @Override
    public ResultMsg loginForRegister(String username, String passport) {
        //先注册后登陆
        register(username,passport);
        return login(username,passport);
    }
}
