package com.lijun.db.redis.limiter;

import cn.hutool.core.date.DateUtil;

import java.util.HashMap;
import java.util.Random;

/**
 * 漏斗限流算法
 *
 * @author : LiJun
 * @date : 2023-06-09 11:06
 **/
public class FunnelRateLimiterTest {

    public static void main(String[] args) {
        FunnelRateLimiter limiter = new FunnelRateLimiter();

        Random random = new Random();
        while (!Thread.interrupted()){
            boolean actionAllowed = limiter.isActionAllowed("Jun", "login", 10, 30);
            System.out.println(DateUtil.now() +":"+ actionAllowed);
            try {
                Thread.sleep(100 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class FunnelRateLimiter {
        private HashMap<String, Funnel> funnelMap = new HashMap<>();

        /**
         * 在指定时间范围内是否可以执行
         *
         * @param userId    用户ID
         * @param action    执行的操作
         * @param maxTimes  最大执行次数
         * @param timeRange 时间范围(单位秒)
         * @return boolean
         */
        public boolean isActionAllowed(String userId, String action, int maxAllowedTimes, float timeRange) {
            String key = String.format("%s:%s", userId, action);
            Funnel funnel = funnelMap.get(key);
            if (funnel == null) {
                funnel = new Funnel(maxAllowedTimes, maxAllowedTimes / timeRange);
                funnelMap.put(key, funnel);
            }

            return funnel.watering(1);
        }
    }

    static class Funnel {

        /**
         * 漏斗容量
         */
        int capacity;
        /**
         * 剩余容量
         */
        int leftQuota;
        /**
         * 漏水速率
         */
        float leakingRate;
        /**
         * 最近一次漏水的时间毫秒值
         */
        long leakingTs;

        public Funnel(int capacity, float leakingRate) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.leakingTs = System.currentTimeMillis();
        }

        /**
         * 计算当前时间下的剩余空间
         */
        public void makeSpace() {
            long nowTs = System.currentTimeMillis();
            long deltaTs = nowTs - leakingTs;
            //计算从最后一次漏水到当前时刻所漏的水量
            int deltaQuota = (int) (deltaTs * leakingRate / 1000);

            //间隔时间过长，整数数值溢出
            if (deltaQuota < 0) {
                this.leftQuota = capacity;
                this.leakingTs = nowTs;
            }
            //腾出空间过小，暂不计算
            if (deltaQuota < 1) {
                return;
            }
            this.leftQuota += deltaQuota;
            this.leakingTs = nowTs;
            if (this.leftQuota > this.capacity) {
                this.leftQuota = this.capacity;
            }
        }

        /**
         * 加水
         *
         * @param quota 加水数量
         * @return 是否可以继续加水
         */
        public boolean watering(int quota) {
            makeSpace();
            if (this.leftQuota >= quota) {
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }
}
