package com.lijun.base.annotation.extend;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author : LiJun
 * @date : 2020-02-29 18:22
 * 注解继承性测试
 **/
@AnnotationChild(extendValue = "extendValue")
public class TestExtend {

    public static void main(String[] args){

        //spring 获取注解
        AnnotaionBase base = AnnotationUtils.findAnnotation(TestExtend.class, AnnotaionBase.class);
        System.out.println(base.toString());

        AnnotationChild child = AnnotationUtils.findAnnotation(TestExtend.class, AnnotationChild.class);
        System.out.println(child.toString());

        //获取组合注解，会使注解覆盖
        AnnotaionBase base2 = AnnotatedElementUtils.findMergedAnnotation(TestExtend.class, AnnotaionBase.class);
        System.out.println(base2.toString());
    }

}
