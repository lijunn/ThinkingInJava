package com.lijun.pattern.proxy.dynamicproxy;

import com.lijun.pattern.proxy.dynamicproxy.cglibproxy.CglibMeiPo;
import com.lijun.pattern.proxy.dynamicproxy.gpproxy.GPMeiPo;
import com.lijun.pattern.proxy.dynamicproxy.gpproxy.GPProxy;
import com.lijun.pattern.proxy.dynamicproxy.jdkproxy.JDKMeiPo;

/**
 * @author : LiJun
 * @date : 2020-04-01 10:21
 *
 * JDK是采用读取接口的信息
 * CGLib覆盖父类方法
 * 目的：都是生成一个新的类，去实现增强代码逻辑的功能
 *
 * JDK Proxy 对于用户而言，必须要有一个接口实现，目标类相对来说复杂
 * CGLib 可以代理任意一个普通的类，没有任何要求
 *
 * CGLib 生成代理逻辑更复杂，效率,调用效率更高，生成一个包含了所有的逻辑的FastClass，不再需要反射调用
 * JDK Proxy生成代理的逻辑简单，执行效率相对要低，每次都要反射动态调用
 **/
public class DynamicProxyTest {

    public static void main(String[] args) {
        Girl girl = new Girl();

        //JDK代理
//        Person person = (Person) new JDKMeiPo().getProxyInstance(girl);

        //GP代理
//        Person person = (Person) new GPMeiPo().getProxyInstance(girl);

        //Cglib代理
        Person person = (Person) new CglibMeiPo().getProxyInstance(Girl.class);


        //测试代理
        person.findLove();

        //测试代理,有参数有返回值的方法
        System.out.println("\n=======");
        int num = person.study("人类简史", 100);
        System.out.println(num);
    }
}
