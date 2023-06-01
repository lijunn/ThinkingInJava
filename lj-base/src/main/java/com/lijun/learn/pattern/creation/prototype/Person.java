package com.lijun.learn.pattern.creation.prototype;

import java.io.Serializable;
import java.util.List;

/**
 * @author : LiJun
 * @date : 2020-04-02 17:52
 **/
public class Person implements Cloneable,Prototype, Serializable {

    private String name;

    private int age;

    private List<String> hobbies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Person{" +
                "hashcode='" + hashCode() + '\'' +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobbies=" + hobbies +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //调用jdk 的clone方法，快速克隆该对象
        return super.clone();
    }

    @Override
    public Prototype getShallowCloneInstance() throws CloneNotSupportedException {
        return (Prototype) this.clone();
    }

    @Override
    public Prototype getDeepCloneInstance(Prototype prototype) {
        //使用序列化深克隆对象，不要忘了要实现 Serializable
        return PrototypeUtils.getSerializInstance(prototype);
    }
}
