package com.lijun.spring.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @author : LiJun
 * @date : 2020-04-16 14:23
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPRequestParam {

    String value() default "";

    boolean required() default true;

    String defaultValue() default "\n\t\t\n\t\t\n\ue000\ue001\ue002\n\t\t\t\t\n";
}
