package com.lijun.learn.chapter09.factory2;

import com.lijun.learn.chapter09.shape.Shape;

/**
 * 工厂模式：为每一种类型创建一个工厂，相对于简单工厂，可以去除 if-else 的判断，不同的实现类在单独的工厂实例化
 */
public interface ShapeFactory {

    Shape getShape();
}
