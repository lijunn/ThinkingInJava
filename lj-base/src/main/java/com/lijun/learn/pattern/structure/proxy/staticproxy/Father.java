package com.lijun.learn.pattern.structure.proxy.staticproxy;

/**
 * @author : LiJun
 * @date : 2020-04-01 10:04
 **/
public class Father {

    private Son son;

    public Father(Son son) {
        this.son = son;
    }

    public void findLove(){
        System.out.println("物色对象。。筛选条件。。");
        son.findLove();
        System.out.println("双方父母确立关系。。。");
    }
}
