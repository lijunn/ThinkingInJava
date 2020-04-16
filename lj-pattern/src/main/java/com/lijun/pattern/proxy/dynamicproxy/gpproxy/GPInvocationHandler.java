package com.lijun.pattern.proxy.dynamicproxy.gpproxy;

import java.lang.reflect.Method;

/**
 * @author : LiJun
 * @date : 2020-04-01 10:57
 **/
public interface GPInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
