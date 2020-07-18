package com.lj.springboot.factorybean;

import com.lj.springboot.LjSpringbootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * FactoryBean 在IOC容器中是一中特殊的Bean，用于生产其他的Bean
 */
@SpringBootApplication
class FactoryBeanTest {

    public static void main(String[] args) throws Exception{
        ApplicationContext context = SpringApplication.run(LjSpringbootApplication.class, args);
        testFactoryBean(context);
    }

    public static void testFactoryBean(ApplicationContext context) throws Exception{
        //想要直接获取工厂Bean的实例，需要加上 & 前缀
        PhoneFactoryBean phoneFactoryBean = (PhoneFactoryBean) context.getBean("&phoneFactoryBean");
        //获取工厂Bean所生产的Bean，这里会先获取 PhoneFactoryBean 然后判断如果是FactoryBean类型的Bean，就去获取其生产的Bean
        Phone phone1 = (Phone) context.getBean("phoneFactoryBean");

        Phone phone2 = phoneFactoryBean.getObject();

        Phone phone3 = (Phone) context.getBean("phone");

        System.out.println("phone1: "+phone1+"  "+phone1.getCreateFrom());
        System.out.println("phone2: "+phone2+"  "+phone2.getCreateFrom());
        System.out.println("phone3: "+phone3+"  "+phone3.getCreateFrom());

    }

}
