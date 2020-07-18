package com.lj.springboot.factorybean;

import org.springframework.stereotype.Component;


/**
 * @author : LiJun
 * @date : 2020-05-14 13:22
 **/
@Component
public class Phone {

    String createFrom;

    public String getCreateFrom() {
        return createFrom;
    }

    public Phone() {
    }

    public Phone(String createFrom) {
        this.createFrom = createFrom;
    }
}
