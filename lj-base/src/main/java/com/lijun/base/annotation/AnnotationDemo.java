package com.lijun.base.annotation;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : LiJun
 * @date : 2019-11-12 17:33
 **/
public class AnnotationDemo {


    @Override
    @MyAnnotation(author = "NoteShare", comments = "Main method", date = "June 12 2015", revision = 1,fontColor= MyAnnotation.FontColor.BLUE)
    public String toString() {
        return "Overriden toString method";
    }
    @Deprecated
    @MyAnnotation(comments = "deprecated method", date = "June 12 2015",value={1,2,3})
    public static void outdatedMethod() {
        System.out.println("outdated method, don't use it.");
    }
    @MyAnnotation(author = "NoteShare", comments = "Main method", date = "June 12 2015", revision = 10)
    public static void genericsTest() throws FileNotFoundException {
        List<String> l = new ArrayList<String>();
        l.add("abc");
        outdatedMethod();
    }


    @AliasAnnotation(path = "/test")
    public void aliasTest(){

    }
}
