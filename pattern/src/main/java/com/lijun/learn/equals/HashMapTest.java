package com.lijun.learn.equals;

import java.util.HashMap;
import java.util.HashSet;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

/**
 * @author : LiJun
 * @date : 2019-07-15 16:23
 **/
public class HashMapTest {

    public static void main(String[] args){

        Key key1 = new Key(1);
        Key key2 = new Key(1);

        println("k1.equals(k2) = "+key1.equals(key2));
        //打印结果： k1.equals(k2) = true
        //说明 key1 和 key2 是相等的对象

        Object o1 = new Object();
        Object o2 = new Object();

        //HashMap 的key 应该是唯一性，因为没有重写 hashCode 所以出现了同样的key
        HashMap<Key, Object> hashMap = new HashMap<>();
        hashMap.put(key1,o1);
        hashMap.put(key2,o2);

        println("hashMap"+hashMap.toString());
        //打印结果：hashMap{Key{num=1}=java.lang.Object@14ae5a5, Key{num=1}=java.lang.Object@7f31245a}

    }

    static class Key{

        private int num;

        public int getNum() {
            return num;
        }

        public Key(int num) {
            this.num = num;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Key){
                return ((Key) obj).getNum() == this.num;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Key{" +
                    "num=" + num +
                    '}';
        }
    }
}
