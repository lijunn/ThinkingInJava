package com.lj.springboot.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : LiJun
 * @date : 2020-07-18 11:53
 **/
@Import(MyBeanRegistrar.class)
@Component
public class Car {

    /**
     * @Autowired 注入
     */
    @Autowired
    Headlight headlight;

    Engine engine;

    Wheel wheel;

    Clutch clutch;

    /**
     * 普通属性注入
     */
    @Value(value = "${car.brand}")
    String carBrand;
    @Value(value = "#{${car.colorList}}")
    ArrayList colorList;
    @Value(value = "#{${car.modelMap}}")
    Map<String,String> modelMap;

    public Car() {
    }

    /**
     * 测试构造函数注入
     */
    public Car(Wheel wheel) {
        System.out.println("构造方法注入");
        this.wheel = wheel;
    }

    /**
     * 测试set方法根据名称自动注入，方法名称必须为 setEngine
     */
    public void setEngine(Engine engine) {
        System.out.println("set根据名称注入");
        this.engine = engine;
    }

    /**
     * 测试set方法根据类型自动注入,方法名称可以随便写
     */
    public void setClutchTest(Clutch clutch) {
        System.out.println("set根据类型注入");
        this.clutch = clutch;
    }

    @Override
    public String toString() {
        return "Car{" +
                "\nheadlight = " + headlight +
                "\nengine    = " + engine +
                "\nwheel     = " + wheel +
                "\nclutch    = " + clutch +
                "\ncarBrand    = " + carBrand +
                "\ncolorList    = " + colorList +
                "\nmodelMap    = " + modelMap +
                "\n}";
    }


}
