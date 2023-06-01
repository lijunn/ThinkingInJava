package com.lijun.base.pattern.structure.decorate;

/**
 * @author : LiJun
 * @date : 2020-04-10 16:43
 * 添加香肠装饰
 **/
public class SausageDecorate extends Pancakes {

    private Pancakes pancakes;

    public SausageDecorate(Pancakes pancakes) {
        this.pancakes = pancakes;
    }

    @Override
    public String getName() {
        return pancakes.getName()+" +1个香肠";
    }

    @Override
    public int getPrice() {
        return pancakes.getPrice()+2;
    }
}
