package com.lijun.db.redis.queue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.resps.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : LiJun
 * @date : 2023-06-01 10:19
 *
 * redis 实现延时队列
 **/
public class RedisDelayQueue {

    public static final String QUEUE_NAME = "MY_MSG_QUEUE";

    /**消息消费者**/
    private final HashMap<String,MsgReceiver> receiverHashMap = new HashMap<>();
    /**处理消息线程池**/
    private final ThreadPoolExecutor handleMsgThreadPool;

    private final JedisPool pool;

    private RedisDelayQueue() {
        this.pool = new JedisPool("localhost", 6379);
        this.handleMsgThreadPool = new ThreadPoolExecutor(5,10,100, TimeUnit.SECONDS,new LinkedBlockingDeque());
    }

    private static class InnerClass{
        static final RedisDelayQueue INSTANCE = new RedisDelayQueue();
    }

    public static RedisDelayQueue getInstance(){
        return InnerClass.INSTANCE;
    }

    public void registerReceiver(String topic,MsgReceiver receiver){
        receiverHashMap.put(topic,receiver);
    }

    public void sendMessage(Message msg,Long delayTime){
        try(Jedis jedis = pool.getResource()) {
            String msgStr = JSON.toJSONString(msg);
            jedis.zadd(QUEUE_NAME,System.currentTimeMillis() + delayTime,msgStr);
        }
    }

    public void start(){
        Jedis jedis = pool.getResource();
        while (!Thread.interrupted()){
            //按时间取，只取一条
            List<String> msgList = jedis.zrangeByScore(QUEUE_NAME, 0, System.currentTimeMillis(), 0, 1);
            if (msgList == null || msgList.size() == 0){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }
            String msg = msgList.get(0);
            if (jedis.zrem(QUEUE_NAME, msg) > 0){
                //线程池处理任务
                handleMsgThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Message message = JSON.toJavaObject(JSONObject.parseObject(msg), Message.class);
                        receiverHashMap.get(message.getTopic()).handleMsg(message);
                    }
                });
            }
        }
    }
}
