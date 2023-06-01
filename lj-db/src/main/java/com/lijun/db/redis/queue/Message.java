package com.lijun.db.redis.queue;

import lombok.Data;

/**
 * @author : LiJun
 * @date : 2023-06-01 11:13
 **/
@Data
public class Message {

    private String topic;
    private String content;

    public Message(String topic, String content) {
        this.topic = topic;
        this.content = content;
    }
}
