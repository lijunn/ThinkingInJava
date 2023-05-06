package com.lijun.pattern.creation.singleton.serializable;

import java.io.Serializable;

/**
 * @author : LiJun
 * @date : 2020-03-26 17:03
 * 测试如何解决序列化破解单例
 **/
public class SerializableSingleton implements Serializable {

    private static final SerializableSingleton instance = new SerializableSingleton();

    private SerializableSingleton(){}

    public static SerializableSingleton getInstance() {
        return instance;
    }


    /**
     * 添加该方法，防止序列化反序列化过程中重新创建对象
     * @return
     */
    private Object readResolve(){
        return instance;
    }
}
