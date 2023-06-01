package com.lijun.learn.pattern.creation.singleton.register;

/**
 * @author : LiJun
 * @date : 2020-03-26 15:48
 * 枚举型单例：
 * 实现原理：
 *
 * 饿汉式实现单例
 *
 * 优点：
 *  2.可以防止反射破解单例，因为JDK不允许枚举被反射创建
 *  3.可以防止序列化破解，
 *
 * 缺点：
 *  1.使用了枚举类型，枚举类因为继承了 Enum 类，无法再继承其他类
 *  2.恶汉式
 **/
public enum  EnumSingleton {

    /**实例对象*/
    INSTANCE;


    public static EnumSingleton getInstance(){
        return INSTANCE;
    }

}


/**反编译后的 EnumSingleton 类

 //继承自枚举类
public final class EnumSingleton extends Enum
{

    public static EnumSingleton[] values()
    {
        return (EnumSingleton[])$VALUES.clone();
    }

    public static EnumSingleton valueOf(String name)
    {
        return (EnumSingleton)Enum.valueOf(com/lijun/pattern/singleton/register/EnumSingleton, name);
    }

    //构造函数私有化
    private EnumSingleton(String s, int i)
    {
        super(s, i);
    }

    public static EnumSingleton getInstance()
    {
        return INSTANCE;
    }

    public static final EnumSingleton INSTANCE;
    private static final EnumSingleton $VALUES[];

    //使用的也是静态代码块，恶汉式加载
    static
    {
        INSTANCE = new EnumSingleton("INSTANCE", 0);
        $VALUES = (new EnumSingleton[] {
                INSTANCE
        });
    }
}

 */
