package com.lijun.learn.chapter09.factory;

import com.lijun.learn.chapter09.shape.Circle;
import com.lijun.learn.chapter09.shape.Shape;
import com.lijun.learn.chapter09.shape.Square;

/**
 * 简单工厂
 */
public class ShapeSimpleFactory {

    public static final String SHAPE_TYPE_CIRCLE = "CIRCLE";
    public static final String SHAPE_TYPE_SQUARE = "SQUARE";


    public static Shape getShape(String shapeType){

        if (shapeType == null){
            return null;
        }

       switch (shapeType){
           case SHAPE_TYPE_CIRCLE:
               return new Circle();
           case SHAPE_TYPE_SQUARE:
               return new Square();
           default:
               return null;
       }
    }
}
