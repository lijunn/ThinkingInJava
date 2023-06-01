package com.lijun.learn.pattern.creation.singleton;

import com.lijun.learn.pattern.creation.singleton.lazy.LazyInnerClassSingleton;
import com.lijun.learn.pattern.creation.singleton.register.EnumSingleton;

import java.lang.reflect.Constructor;

/**
 * @author : LiJun
 * @date : 2020-03-26 15:40
 *
 * 反射破解单例
 *
 * 1.无论是恶汉式还是懒汉式都可以被反射调用构造函数进行破解
 * 解决办法：在构造函数中判断如果实例已经创建则抛异常，让调用者使用正确的方式获取单例对象
 *
 *  if (null != instance){
 *       throw new Exception("装逼失败，请使用正确方法获取实例");
 *  }
 *
 * 2.使用枚举类型单例
 *
 * 因为枚举类型在被反射创建时会抛出异常，
 * Cannot reflectively create enum objects，
 * JDK 在 newInstance 方法中做了判断，枚举类型不能够被创建
 *
 **/
public class ReflectSingletonTest {

    public static void main(String[] args) {


        try {
            //反射创建对象
            Constructor constructor = LazyInnerClassSingleton.class.getDeclaredConstructor(null);
            constructor.setAccessible(true);
            LazyInnerClassSingleton lazyInnerClassSingleton1 = (LazyInnerClassSingleton) constructor.newInstance(null);


            //单例方法获取对象
            LazyInnerClassSingleton lazyInnerClassSingleton2 = LazyInnerClassSingleton.getInstance();

            System.out.println("反射获取对象："+lazyInnerClassSingleton1);
            System.out.println("单例获取对象："+lazyInnerClassSingleton2);



            //反射创建枚举对象
            Constructor<EnumSingleton> enumSingletonConstructor = EnumSingleton.class.getDeclaredConstructor(String.class,int.class);
            enumSingletonConstructor.setAccessible(true);
            EnumSingleton enumSingleton = enumSingletonConstructor.newInstance("lijun",666);
            System.out.println("反射获取枚举单例："+enumSingleton);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
