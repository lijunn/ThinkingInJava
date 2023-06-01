package com.lijun.learn.pattern.creation.singleton;

import com.lijun.learn.pattern.creation.singleton.register.EnumSingleton;

import java.io.*;

/**
 * @author : LiJun
 * @date : 2020-03-26 17:01
 *
 * 序列化破解单例
 *
 * 一个对象被序列化然后还原后会是不同的对象，这就破坏了单例
 *
 * 解决方法：
 *
 * 1.为对象添加下面方法，返回已创建的实例
 *
 *  private Object readResolve(){
 *         return instance;
 *  }
 *
 * 原理：在反序列化 readObject() 方法中还是会创建出新的对象，只不过JDK 通过反射调用 readResolve 方法，然后覆盖新创建的对象，
 *      所以还是会造成内存浪费有从根本上解决。
 *
 * 2.使用枚举单例
 *
 * 原理：1.枚举对象会根据类名 保存到枚举 Class对象中的集合中。
 *      2.在反序列化 readObject() 方法中，会从检查如果是枚举类型则会
 *      通过类名和 Class 对象类找到一个唯一的枚举对象。因此，枚举对 象不可能被类加载器加载多次
 *
 **/
public class SerializableSingletonTest {

    public static void main(String[] args) {

//        SerializableSingleton s1 = null;
//        SerializableSingleton s2 = SerializableSingleton.getInstance();
//        String fileName = "SerializableSingleton.obj";

        EnumSingleton s1 = null;
        EnumSingleton s2 = EnumSingleton.getInstance();
        String fileName = "EnumSingleton.obj";

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();


            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            s1 = (EnumSingleton)ois.readObject();
            ois.close();

            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s1 == s2);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
