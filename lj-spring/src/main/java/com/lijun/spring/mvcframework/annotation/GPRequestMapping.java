package com.lijun.spring.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @author : LiJun
 * @date : 2020-04-16 14:20
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GPRequestMapping {

    String value() default "";
}


