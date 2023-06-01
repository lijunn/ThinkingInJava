package com.lijun.base.construcorInit;

import static com.lijun.base.PrintUtil.print;

public class PolyConstructors {



    public static void main(String[] args){


        RoundGlyph roundGlyph = new RoundGlyph(5);

        roundGlyph.getInnerClassStatic();
    }

    static class GlyphValue{
        GlyphValue(String str){
            print(str+" init");
        }
    }


    static class Glyph{
        //静态变量初始化
        public static GlyphValue aStaticValue = new GlyphValue("Glyph-static-value");
        //成员变量初始化
        private GlyphValue numberValue = new GlyphValue("Glyph-number-value");

        void draw(){
            print("Glyph.draw()");
        }

        Glyph(){
            print("Glyph init");
        }

        GlyphValue getInnerClassStatic(){
            return InnerClass.innerClassStatic;
        }

        //静态内部类，只有在被第一次使用时才会被初始化
        private static class InnerClass{
            public static GlyphValue innerClassStatic = new GlyphValue("innerClassStatic");
        }
    }

    static class RoundGlyph extends Glyph{
        //静态变量初始化
        public static GlyphValue aStaticValue = new GlyphValue("RoundGlyph-static-value");
        //成员变量初始化
        private GlyphValue numberValue = new GlyphValue("RoundGlyph-number-value");

        private int radius = 1;

        RoundGlyph(int r){
            print("RoundGlyph init");
        }

        @Override
        void draw() {
            print("RoundGlyph.draw() radius="+radius);
        }

    }



}
