package com.lijun.pattern.creation.prototype;

import java.io.*;

/**
 * @author : LiJun
 * @date : 2020-04-02 17:38
 * 原型工具类，通过序列化复制一个新实例，
 * 因为JDK在序列化对象时，必定会重新创建一个对象
 **/
public class PrototypeUtils {

    public static Prototype getSerializInstance(Prototype prototype){
        Prototype newPrototype = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(prototype);
//            bos.flush();

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            newPrototype = (Prototype) ois.readObject();


            bos.close();
            oos.close();
            bis.close();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newPrototype;
    }
}
