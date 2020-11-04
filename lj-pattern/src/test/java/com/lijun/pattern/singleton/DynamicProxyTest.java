package com.lijun.pattern.singleton;

/**
 * @author : LiJun
 * @date : 2020-11-04 20:48
 **/
public class DynamicProxyTest {

    public static void main(String[] args) {

    }

    class Phone {
        public void call(){
            System.out.println("打电话中。。。");
        }
    }

    class DynamicProxy {

        public Object getProxyObj(Object target) throws Exception{
            Class<?> clazz = target.getClass();
            Class<?>[] interfaces = clazz.getInterfaces();
            if (interfaces == null || interfaces.length == 0){
                throw new Exception("can not find interface");
            }
            interfaces[0];

            return null;
        }

    }


}
