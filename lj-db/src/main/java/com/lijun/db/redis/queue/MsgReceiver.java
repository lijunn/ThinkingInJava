package com.lijun.db.redis.queue;

/**
 * @author : LiJun
 * @date : 2023-06-01 14:11
 **/
public interface MsgReceiver {
    void handleMsg(Message msg);
}
