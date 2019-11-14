package com.thinking.in.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : LiJun
 * @date : 2019-06-21 10:40
 * JDK 动态代理 ,缺点 被代理类必须要实现某个接口
 **/
public class JDKProxy {

    static class MyHandler implements InvocationHandler{

        /**被代理对象*/
        private Object proxy;
        public MyHandler(Object proxy) {
            this.proxy = proxy;
        }

        /**
         * @param Object 代理对象
         * @param method 调用方法
         * @param args 方法参数
         * @return 返回值
         * @throws Throwable
         */
        @Override
        public Object invoke(Object o, Method method, Object[] args) throws Throwable {

            System.out.println(method.getName()+"--方法调用前");

            //调用被代理对象的方法
            Object returnValue = method.invoke(proxy, args);

            System.out.println(method.getName()+"--方法调用后");

            return returnValue;
        }
    }

    /**
     * 获取代理对象
     * @param interfaces 被代理对象所有实现的接口
     * @param proxy 被代理对象
     * @return 代理对象
     */
    public static Object getProxy(Class<?>[] interfaces,Object proxy){
        return Proxy.newProxyInstance(interfaces[0].getClassLoader(), interfaces, new MyHandler(proxy));
    }

}
