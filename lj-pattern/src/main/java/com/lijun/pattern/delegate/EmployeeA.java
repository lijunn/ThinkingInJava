package com.lijun.pattern.delegate;

/**
 * @author : LiJun
 * @date : 2020-04-02 09:52
 * 员工A 负责加密
 **/
public class EmployeeA implements Employee {
    @Override
    public void doWork(String command) {
        System.out.println("我是员工A，现在开始工作，执行命令："+command);
    }
}
