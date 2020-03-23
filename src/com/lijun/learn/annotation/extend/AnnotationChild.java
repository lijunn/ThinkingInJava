package com.lijun.learn.annotation.extend;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author : LiJun
 * @date : 2020-02-29 18:16
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@AnnotaionBase //继承  AnnotaionBase 注解
public @interface AnnotationChild {

    @AliasFor(annotation = AnnotaionBase.class, attribute = "value")
    String extendValue() default "child";
}
