package com.lijun.db.redis.hyperloglog;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author : LiJun
 * @date : 2023-06-13 12:03
 **/
public class PfTest {

    public static void main(String[] args) {
        JedisPool pool = new JedisPool("localhost", 6379);

        try (Jedis jedis = pool.getResource()){
            for (int i=1;i<=10000;i++){
                jedis.pfadd("uv","user"+i);
                long uv = jedis.pfcount("uv");
                System.out.println("total:"+i+"  uv:"+uv);
            }
        }
    }
}
