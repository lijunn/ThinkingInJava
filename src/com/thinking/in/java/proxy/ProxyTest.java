package com.thinking.in.java.proxy;

/**
 * @author : LiJun
 * @date : 2019-06-24 14:42
 **/
public class ProxyTest {

    public static void main(String[] args){

        //测试 JDK 动态代理
        Actor proxy = (Actor) JDKProxy.getProxy(new Class[]{Actor.class}, new ActorA());
        proxy.play();


        CGlibProxy cGlibProxy = new CGlibProxy(new ActorA());
        Actor cgProxy = (Actor) cGlibProxy.getProxy();
        cgProxy.playB();
    }
}
