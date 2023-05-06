package com.lijun.pattern.behavior.delegate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : LiJun
 * @date : 2020-04-02 09:52
 * 经理 负责任务分发，接收 Boss 的委托
 **/
public class Leader implements Employee {

    private static final Map<String,Employee> employeeMap = new ConcurrentHashMap<>();
    private static volatile Leader instance;

    /**注册式单例，策略模式*/
    static {
        employeeMap.put("加密",new EmployeeA());
        employeeMap.put("登陆",new EmployeeB());
    }

    private Leader() {
    }

    /**懒汉式双重检查单例*/
    public static Leader getInstance(){
        if (null == instance){
            synchronized (Leader.class){
                if (null == instance){
                    instance = new Leader();
                }
            }
        }

        return instance;
    }




    @Override
    public void doWork(String command) {
        System.out.println("经理--分发任务...");
        employeeMap.get(command).doWork(command);
    }
}
