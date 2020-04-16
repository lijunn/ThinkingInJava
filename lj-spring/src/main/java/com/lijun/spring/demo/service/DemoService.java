package com.lijun.spring.demo.service;

import com.lijun.spring.mvcframework.annotation.GPService;

/**
 * @author : LiJun
 * @date : 2020-04-16 11:00
 **/
@GPService
public class DemoService implements IDemoService {
    public String getName(String name) {
        return "My Name is "+name;
    }
}
