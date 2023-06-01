package com.lijun.learn.pattern.behavior.delegate;

/**
 * @author : LiJun
 * @date : 2020-04-02 10:21
 **/
public class Boss {


    public void doWork(String command, Leader leader){
        System.out.println("Boss--委派任务给经理...");
        leader.doWork(command);
    }
}
