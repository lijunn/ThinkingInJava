package com.lijun.learn.chapter09.factory2;

import com.lijun.learn.chapter09.shape.Shape;
import com.lijun.learn.chapter09.shape.Square;

public class SquareFactory implements ShapeFactory {
    @Override
    public Shape getShape() {
        return new Square();
    }
}
