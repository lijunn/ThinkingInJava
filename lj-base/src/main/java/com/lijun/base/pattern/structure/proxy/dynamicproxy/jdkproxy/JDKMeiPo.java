package com.lijun.base.pattern.structure.proxy.dynamicproxy.jdkproxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : LiJun
 * @date : 2020-04-01 10:09
 * JDK 媒婆代理，实现 InvocationHandler
 *
 * 缺点：被代理对象需要实现接口
 *
 * 实现原理：
 *
 * 1.动态生成代理对象的源码，代理对象实现了被代理对象的接口，
 *  并且在实现的方法中调用 InvocationHandler 的 invoke 方法，
 *  通过反射扫描被代理对象的方法将其传入 invoke 方法中，最后在 InvocationHandler 进行代理增强。
 *
 * 2.将源码写成 $Proxy0.java 文件(后面数字)
 * 3.编译代理对象源码生成 $Proxy0.class 文件
 * 4.使用类加载器加载代理对象到内存，并返回代理对象
 *
 **/
public class JDKMeiPo implements InvocationHandler {

    private Object obj;

    public Object getProxyInstance(Object obj){
        this.obj = obj;
        Class<?>[] interfaces = obj.getClass().getInterfaces();
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),interfaces,this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke:"+method.getName());

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
