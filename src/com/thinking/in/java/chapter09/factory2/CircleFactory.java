package com.thinking.in.java.chapter09.factory2;

import com.thinking.in.java.chapter09.shape.Circle;
import com.thinking.in.java.chapter09.shape.Shape;

public class CircleFactory implements ShapeFactory {
    @Override
    public Shape getShape() {
        return new Circle();
    }
}
