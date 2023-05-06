package com.lijun.pattern.structure.decorate;

/**
 * @author : LiJun
 * @date : 2020-04-10 16:21
 * 添加鸡蛋装饰器
 **/
public class EggDecorate extends Pancakes {

    private Pancakes pancakes;

    public EggDecorate(Pancakes pancakes) {
        this.pancakes = pancakes;
    }

    @Override
    public String getName() {
        return pancakes.getName()+" + 1个鸡蛋";
    }

    @Override
    public int getPrice() {
        return pancakes.getPrice()+1;
    }
}
