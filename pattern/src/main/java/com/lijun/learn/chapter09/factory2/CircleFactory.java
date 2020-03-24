package com.lijun.learn.chapter09.factory2;

import com.lijun.learn.chapter09.shape.Circle;
import com.lijun.learn.chapter09.shape.Shape;

public class CircleFactory implements ShapeFactory {
    @Override
    public Shape getShape() {
        return new Circle();
    }
}
