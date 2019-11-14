package com.thinking.in.java.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author : LiJun
 * @date : 2019-06-24 17:10
 * CGlib是一个字节码增强库，为AOP等提供了底层支持。支持对没有接口的类进行代理
 **/
public class CGlibProxy implements MethodInterceptor {

    private Object target;

    public CGlibProxy(Object target){
        this.target = target;
    }

    public Object getProxy(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置监听器
        en.setCallback(this);
        //4. 创建子类(代理对象)
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("--方法调用前");
        Object returnValue = method.invoke(target, args);
        System.out.println("--方法调用后");

        return returnValue;
    }
}
