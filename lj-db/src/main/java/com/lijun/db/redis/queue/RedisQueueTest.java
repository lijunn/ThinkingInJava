package com.lijun.db.redis.queue;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.resps.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : LiJun
 * @date : 2023-06-01 10:19
 **/
public class RedisQueueTest {
    public static void main(String[] args) {
        RedisDelayQueue delayQueue = RedisDelayQueue.getInstance();
        delayQueue.registerReceiver("News",new NewsReceiver());
        delayQueue.registerReceiver("Notice",new NoticeReceiver());

        //发送消息
        new Thread(() -> {
            for (int i = 0;i<10;i++){
                delayQueue.sendMessage(new Message("News","马斯克访华"+i),2000L);
                delayQueue.sendMessage(new Message("Notice","消息提醒"+i),4000L);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        delayQueue.start();
    }




}
