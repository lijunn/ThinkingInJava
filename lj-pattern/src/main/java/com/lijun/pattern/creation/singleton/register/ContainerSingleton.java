package com.lijun.pattern.creation.singleton.register;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : LiJun
 * @date : 2020-03-26 16:52
 * 容器式单例
 **/
public class ContainerSingleton implements Serializable {

    private static Map<String,Object> ioc = new ConcurrentHashMap<>();

    private ContainerSingleton(){}

    public static Object getBean(String className){
        synchronized (ioc){

            if (ioc.containsKey(className)){
                return ioc.get(className);
            }else {
                try {
                    Constructor<?> constructor = Class.forName(className).getDeclaredConstructor();
                    Object obj = constructor.newInstance();

                    ioc.put(className,obj);

                    return obj;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
}
