package com.lijun.db.redis.mq;

import cn.hutool.core.date.DateUtil;

/**
 * @author : LiJun
 * @date : 2023-06-01 14:12
 **/
public class NewsReceiver implements MsgReceiver {
    @Override
    public void handleMsg(Message msg) {
        System.out.println(DateUtil.now()+" NewsReceiver:"+msg.getContent());
    }
}
