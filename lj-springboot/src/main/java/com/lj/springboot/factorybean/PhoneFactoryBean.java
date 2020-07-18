package com.lj.springboot.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author : LiJun
 * @date : 2020-05-14 13:22
 **/
@Component
@Lazy
public class PhoneFactoryBean implements FactoryBean<Phone> {
    @Override
    public Phone getObject() throws Exception {
        return new Phone("由PhoneFactoryBean创建");
    }

    @Override
    public Class<?> getObjectType() {
        return Phone.class;
    }
}
