package com.lijun.learn.proxy;

/**
 * @author : LiJun
 * @date : 2019-06-24 14:51
 **/
public class ActorA implements Actor {
    @Override
    public void play() {
        System.out.println("com.proxy.ActorA play");
    }

    @Override
    public void playB() {
        System.out.println("com.proxy.ActorA play B");
    }
}
