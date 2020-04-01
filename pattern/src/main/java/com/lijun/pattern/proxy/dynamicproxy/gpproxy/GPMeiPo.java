package com.lijun.pattern.proxy.dynamicproxy.gpproxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : LiJun
 * @date : 2020-04-01 10:09
 *
 **/
public class GPMeiPo implements GPInvocationHandler {

    private Object obj;

    public Object getProxyInstance(Object obj){
        this.obj = obj;
        Class<?>[] interfaces = obj.getClass().getInterfaces();
        return GPProxy.newProxyInstance(new GPClassLoader(),interfaces,this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        beforeMethod();
        Object result = method.invoke(obj, args);
        afterMethod();

        return result;
    }

    private void beforeMethod() {
        System.out.println("媒婆：物色对象，筛选条件....");
    }

    private void afterMethod() {
        System.out.println("媒婆：ok的话准备办事");
    }


}
