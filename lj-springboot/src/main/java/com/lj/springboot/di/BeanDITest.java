package com.lj.springboot.di;

import com.lj.springboot.LjSpringbootApplication;
import com.lj.springboot.factorybean.Phone;
import com.lj.springboot.factorybean.PhoneFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * spring中对一个Bean的依赖注入有三种方式
 *
 * 1.构造函数注入
 * 2.set注入，根据名称（要求set方法名要匹配注入对象的名称，原理是根据方法名获取bean的名称，然后去IOC容器中查询）
 * 3.set注入，根据类型（对set方法名称无要求，主要是根据方法参数来判断）
 *
 * 具体实现有两种方式，AutowireMode 和 @Autowire
 *
 * 一、AutowireMode 自动注入，spring 调用已经写好的构造方法或者set方法进行注入
 *
 * AUTOWIRE_NO 不开启自动注入，如果不设置，默认是该模式,如果要修改模式的话需要自己实现 ImportBeanDefinitionRegistrar
 * AUTOWIRE_BY_NAME 根据set方法名注入
 * AUTOWIRE_BY_TYPE 根据set方法参数类型注入
 * AUTOWIRE_CONSTRUCTOR 构造函数注入
 * AUTOWIRE_AUTODETECT 自动选择注入方式- 根据有无有参数的构造函数去选择使用，构造函数注入还是根据类型注入
 *
 * 二、@Autowire 用户手动添加注解，spring 解析注解，然后反射设值，注入方式也有两种
 *
 * 1.根据名称set注入
 * 2.根据类型set注入
 * 例如：这里的类型为 Engine ，名称为 engine
 *      @Autowire
 *      Engine engine
 *
 * 默认情况是这种是根据类型，如果同一类型的Bean有多个则根据名称，
 * 如果加上@Qualifier(value="name")指定名称，则使用指定的名称
 *
 */
@SpringBootApplication
class BeanDITest {

//    public static int AutowireMode = AbstractBeanDefinition.AUTOWIRE_NO;
//    运行结果:
//    Car{
//        headlight = com.lj.springboot.di.Headlight@21129f1f
//        engine    = null
//        wheel     = null
//        clutch    = null
//    }

//    public static int AutowireMode = AbstractBeanDefinition.AUTOWIRE_BY_NAME;
//    运行结果:
//    set根据名称注入
//    Car{
//        headlight = com.lj.springboot.di.Headlight@68034211
//        engine    = com.lj.springboot.di.Engine@4f74980d
//        wheel     = null
//        clutch    = null
//    }

//    public static int AutowireMode = AbstractBeanDefinition.AUTOWIRE_BY_TYPE;
//    运行结果
//    set根据类型注入
//    set根据名称注入
//    Car{
//        headlight = com.lj.springboot.di.Headlight@11bb571c
//        engine    = com.lj.springboot.di.Engine@7c51f34b
//        wheel     = null
//        clutch    = com.lj.springboot.di.Clutch@5495333e
//    }

//    public static int AutowireMode = AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR;
//    运行结果
//    Car{
//        headlight = com.lj.springboot.di.Headlight@3e2059ae
//        engine    = null
//        wheel     = com.lj.springboot.di.Wheel@398dada8
//        clutch    = null
//    }

    public static int AutowireMode = AbstractBeanDefinition.AUTOWIRE_AUTODETECT;

    public static void main(String[] args) throws Exception{
        ApplicationContext context = SpringApplication.run(LjSpringbootApplication.class, args);
        test(context);
    }

    public static void test(ApplicationContext context) throws Exception{
        Car car = (Car) context.getBean("car");
        System.out.println(car.toString());
    }

}
