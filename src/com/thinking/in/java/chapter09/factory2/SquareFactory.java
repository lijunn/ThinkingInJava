package com.thinking.in.java.chapter09.factory2;

import com.thinking.in.java.chapter09.shape.Shape;
import com.thinking.in.java.chapter09.shape.Square;

public class SquareFactory implements ShapeFactory {
    @Override
    public Shape getShape() {
        return new Square();
    }
}
