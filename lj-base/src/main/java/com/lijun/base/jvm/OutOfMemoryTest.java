package com.lijun.base.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 内存溢出测试
 * @author : LiJun
 * @date : 2023-07-03 15:21
 **/
public class OutOfMemoryTest {


    public static void main(String[] args) {
//        heapOOM();

//        runtimeConstantPoolOOM();

        javaMethodAreaOOM();
    }

    static class OomObject {

    }



    /**
     * 堆-内存溢出
     * jvm参数：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     */
    public static void heapOOM(){
        ArrayList<OomObject> list = new ArrayList<>();
        while (true){
            list.add(new OomObject());
        }
    }

    /**
     * 运行时常量-池内存溢出
     * jdk7以前， jvm参数：-XX:PermSize=6M -XX:MaxPermSize=6M
     * jdk7及以后， jvm参数：-Xms6m -Xmx6m -XX:+HeapDumpOnOutOfMemoryError
     */
    public static void runtimeConstantPoolOOM(){
        Set<String> set = new HashSet<>();
        short i = 0;
        while (true){
            set.add(String.valueOf(i++).intern());
        }
    }

    /**
     * 方法区-内存溢出
     * jdk8以前，jvm参数：-XX:PermSize=6M -XX:MaxPermSize=6M
     * jdk8及以后，jvm参数：-XX:MetaspaceSize=6M -XX:MaxMetaspaceSize=6M
     */
    public static void javaMethodAreaOOM(){
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OomObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o,objects);
                }
            });
        }
    }
}

