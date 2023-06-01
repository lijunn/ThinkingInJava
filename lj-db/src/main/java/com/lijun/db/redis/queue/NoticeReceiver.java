package com.lijun.db.redis.queue;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author : LiJun
 * @date : 2023-06-01 14:12
 **/
public class NoticeReceiver implements MsgReceiver {
    @Override
    public void handleMsg(Message msg) {
        System.out.println(DateUtil.now()+" NoticeReceiver:"+msg.getContent());
    }
}
