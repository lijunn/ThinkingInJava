package com.thinking.in.java.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : LiJun
 * @date : 2019-06-21 10:40
 * JDK 动态代理 ,缺点 被代理类必须要实现某个接口
 **/
public class DynamicAgent {

    static class MyHandler implements InvocationHandler{

        private Object proxy;
        public MyHandler(Object proxy) {
            this.proxy = proxy;
        }

        /**
         *
         * @param proxy 被代理对象
         * @param method 调用方法
         * @param args 方法参数
         * @return 返回值
         * @throws Throwable
         */
        @Override
        public Object invoke(Object o, Method method, Object[] args) throws Throwable {

            print(method.getName()+"--方法调用前");

            //这里不能使用上面的 o ,会产生递归调用
            Object invoke = method.invoke(proxy, args);

            print(method.getName()+"--方法调用后");

            return invoke;
        }
    }

    /**
     * 获取代理类
     * @param interfaceClass
     * @return
     */
    public static Object agent(Class interfaceClass,Object proxy){
        return Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new MyHandler(proxy));
    }


    public static void print(String str){
        System.out.println(str);
    }
}
