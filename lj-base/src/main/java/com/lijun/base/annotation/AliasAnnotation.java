package com.lijun.base.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author : LiJun
 * @date : 2020-02-29 15:38
 * 别名注解
 **/
@Target({ElementType.METHOD,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AliasAnnotation {



    @AliasFor("value")
    String path() default "";

    @AliasFor("path")
    String value() default "";
}
