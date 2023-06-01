package com.lijun.base.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author : LiJun
 * @date : 2019-11-12 17:35
 * 解析注解
 **/
public class AnnotationParsing {

    public static void main(String[] args){

        Method[] methods = AnnotationDemo.class.getMethods();

        for (Method method:methods){
            System.out.println("+++++++++++++++++++++++++++++++++++");
            //获取被注解修饰的方法
            if (method.isAnnotationPresent(MyAnnotation.class)){
                //获取该方法所有的注解
                Annotation[] annotations = method.getAnnotations();
                for (Annotation anno : annotations) {
                    System.out.println("Annotation in Method \n'"+ method.getName() + "' : " + anno);
                }

                //获取该方法指定的注解
                MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
                System.out.println("comments:"+annotation.comments());
                System.out.println("comments:"+annotation.date());
            }
        }
    }
}
