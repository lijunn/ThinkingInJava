package com.lijun.learn.chapter09;

import com.lijun.learn.chapter09.factory2.CircleFactory;
import com.lijun.learn.chapter09.factory2.SquareFactory;
import com.lijun.learn.chapter09.shape.Shape;

public class MainTest {

    public static void main(String[] args){

        /*使用简单工厂*/

//        //创建圆形
//        Shape shapeC = ShapeSimpleFactory.getShape(ShapeSimpleFactory.SHAPE_TYPE_CIRCLE);
//        shapeC.draw();
//
//        //创建正方形
//        Shape shapeS = ShapeSimpleFactory.getShape(ShapeSimpleFactory.SHAPE_TYPE_SQUARE);
//        shapeS.draw();


        /*使用工厂模式*/
        CircleFactory circleFactory = new CircleFactory();
        Shape shapeC = circleFactory.getShape();
        shapeC.draw();

        SquareFactory squareFactory = new SquareFactory();
        Shape shapeS = squareFactory.getShape();
        shapeS.draw();


    }
}
