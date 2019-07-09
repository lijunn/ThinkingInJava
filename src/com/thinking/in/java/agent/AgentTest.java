package com.thinking.in.java.agent;

/**
 * @author : LiJun
 * @date : 2019-06-24 14:42
 **/
public class AgentTest {

    public static void main(String[] args){

        //测试 JDK 动态代理
//        com.agent.Actor agent = (com.agent.Actor) com.agent.DynamicAgent.agent(com.agent.Actor.class,new com.agent.ActorA());
//        agent.play();
//
//        agent.playB();


        Actor agent = (Actor) MyAgent.getAgent(Actor.class, new ActorA());
        agent.play();

    }
}
