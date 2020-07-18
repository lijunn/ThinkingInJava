package com.lj.springboot.di;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author : LiJun
 * @date : 2020-07-18 11:59
 *  AutowireMode 默认值为 AUTOWIRE_NO 不开启自动注入
 *  如果需要修改则实现 ImportBeanDefinitionRegistrar，重写registerBeanDefinitions
 *  然后使用 @Import(MyBeanRegistrar.class) 将其注解到要修改的类上
 **/
public class MyBeanRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //获取 Car 的 beanDefinition
        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) registry.getBeanDefinition("car");

        //修改自动注入模型
        beanDefinition.setAutowireMode(BeanDITest.AutowireMode);
    }
}
