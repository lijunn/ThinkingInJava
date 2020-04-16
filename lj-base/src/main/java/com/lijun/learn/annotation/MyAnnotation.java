package com.lijun.learn.annotation;

import java.lang.annotation.*;

/**
 * @author : LiJun
 * @date : 2019-11-12 10:11
 **/
@Target({ElementType.METHOD,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyAnnotation {

    enum FontColor{
        BLUE,
        RED,
        GREEN
    }

    int[] value() default {1,2};

    String author() default "JJ";

    String date();

    int revision() default 1;

    String comments();

    FontColor fontColor() default FontColor.BLUE;
}
