package com.lijun.learn.pattern.creation.prototype;

/**
 * @author : LiJun
 * @date : 2020-04-02 17:35
 **/
public interface Prototype {

    /**
     * 浅克隆
     * @return
     * @throws ClassNotFoundException
     */
    Prototype getShallowCloneInstance() throws CloneNotSupportedException;

    /**
     * 深克隆
     * @param prototype
     * @return
     */
    Prototype getDeepCloneInstance(Prototype prototype);
}
