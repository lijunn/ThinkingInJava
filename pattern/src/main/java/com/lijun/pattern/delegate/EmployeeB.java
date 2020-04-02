package com.lijun.pattern.delegate;

/**
 * @author : LiJun
 * @date : 2020-04-02 09:52
 * 员工B 负责登陆
 **/
public class EmployeeB implements Employee {
    @Override
    public void doWork(String command) {
        System.out.println("我是员工B，现在开始工作，执行命令："+command);
    }
}
