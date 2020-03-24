package com.lijun.learn.annotation.extend;

import java.lang.annotation.*;

/**
 * @author : LiJun
 * @date : 2020-02-29 18:16
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AnnotaionBase {

    String value() default "base";
}
