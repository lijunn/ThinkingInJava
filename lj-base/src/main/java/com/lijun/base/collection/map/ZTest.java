package com.lijun.base.collection.map;

import java.util.EnumMap;
import java.util.IdentityHashMap;

/**
 * @author : LiJun
 * @date : 2023-09-22 17:09
 **/
public class ZTest {
    public static void main(String[] args) {

        //如果 key1==key2 那么认为这两个key相等
        IdentityHashMap<String, Integer> identityHashMap = new IdentityHashMap<>();

//        new EnumMap<>()
    }

  public static  enum NameEnum {

        private String name;
  }
}
