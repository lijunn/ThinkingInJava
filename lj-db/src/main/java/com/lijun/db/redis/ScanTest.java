package com.lijun.db.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Scan 命令用于查询redis中的key
 * scan    0      match key99*      count 1000         type string
 *      游标位置    正则匹配     单次遍历的字典槽位数量      value的类型
 * 该命令会返回一个当前查询的游标位置，后续可以根据游标继续往下查，
 * 当返回游标为0时表示遍历结束
 *
 * @author : LiJun
 * @date : 2023-06-13 10:27
 **/
public class ScanTest {

    public static void main(String[] args) {
        JedisPool pool = new JedisPool("localhost", 6379);

        try (Jedis jedis = pool.getResource()){
            for (int i=0;i<10000;i++){
                jedis.set("key"+i,i+"");
            }
        }
    }

}
