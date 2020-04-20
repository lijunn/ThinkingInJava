package com.lijun.spring.demo.controller;

import com.lijun.spring.mvcframework.annotation.GPAutowired;
import com.lijun.spring.mvcframework.annotation.GPController;
import com.lijun.spring.mvcframework.annotation.GPRequestMapping;
import com.lijun.spring.mvcframework.annotation.GPRequestParam;
import com.lijun.spring.demo.service.IDemoService;

/**
 * @author : LiJun
 * @date : 2020-04-16 11:00
 **/
@GPController
@GPRequestMapping("/demo")
public class DemoController {


    @GPAutowired
    IDemoService demoService;

    @GPRequestMapping("/getName")
    public String getName(@GPRequestParam(value = "name") String name,@GPRequestParam(value = "age") Integer age){
        return demoService.getName(name,age);
    }

    @GPRequestMapping("/add")
    public String add(@GPRequestParam(value = "a") Integer a,@GPRequestParam(value = "b") Integer b){
        int sum = a+b;
        return "a + b = "+sum;
    }
}
