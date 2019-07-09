package com.thinking.in.java.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : LiJun
 * @date : 2019-06-24 15:09
 **/
public class MyAgent {

    static class MyHandler implements InvocationHandler {

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

            System.out.println(method.getName()+"--方法调用前");

            //这里不能使用上面的 o ,会产生递归调用
            Object invoke = method.invoke(proxy, args);

            System.out.println(method.getName()+"--方法调用后");

            return invoke;
        }
    }

    public static Object getAgent(Class clazz,Object proxy){
        MyHandler myHandler = new MyHandler(proxy);


        Actor actor = new Actor() {

            @Override
            public void play() {
                //获取当前方法名称
                String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
                invokeMethod(clazz,methodName,myHandler,proxy);
            }

            @Override
            public void playB() {
                //获取当前方法名称
                String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
                invokeMethod(clazz,methodName,myHandler,proxy);
            }
        };
        return actor;
    }


    public static void invokeMethod(Class clazz,String methodName,MyHandler myHandler,Object proxy){

        //获取当前类所有方法
        Method[] ms = clazz.getMethods();

        for (Method method : ms) {
            // 获取方法参数列表
            Class[] paramTypes = method.getParameterTypes();

            if (method.getName().equals(methodName)) {
                try {
                    myHandler.invoke(proxy, method, paramTypes);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }
}
