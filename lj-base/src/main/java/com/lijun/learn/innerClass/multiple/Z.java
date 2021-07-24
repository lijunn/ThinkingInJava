package com.lijun.learn.innerClass.multiple;

import static com.lijun.learn.PrintUtil.print;

/**
 * 用内部类实现“多继承”
 * Z 继承了D,而且通过 makeE 方法可以获取抽象类E的实现，从而相当于继承了 E
 */
public class Z extends D{

    @Override
    public void runD() {
        super.runD();
        print("Z  runD ");
    }

    public E makeE(){
        return new E() {

            @Override
            public void runE() {
                print("E  runE ");
            }
        };
    }
}
