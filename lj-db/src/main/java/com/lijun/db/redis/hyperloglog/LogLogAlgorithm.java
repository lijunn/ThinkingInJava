package com.lijun.db.redis.hyperloglog;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 模拟 HyperLogLog 算法
 * @author : LiJun
 * @date : 2023-06-05 10:57
 **/
public class LogLogAlgorithm {

    static class BitKeeper {
        private int maxbits;

        /**
         * 随机一个数，获取地位连续为0的个数
         */
        public void random() {
            long value = ThreadLocalRandom.current().nextLong(2L << 32);
            int bits = lowZeros(value);
            if (bits > this.maxbits) {
                this.maxbits = bits;
            }
        }

        /**
         * 计算出低位连续为0的个数
         * 先左移一位然后再右移一位，如果和原数相同则表示为连续0
         */
        public int lowZeros(long value) {
            int i = 1;
            for (; i < 32; i++) {
                if (value >> i << i != value) {
                    break;
                }
            }
            return i - 1;
        }
    }

    static class Experiment {
        private int n;
        private BitKeeper keeper;

        public Experiment(int n) {
            this.n = n;
            this.keeper = new BitKeeper();
        }

        //随机取n个随机数，计算低位连续为0的最大个数
        public void work() {
            for (int i = 0; i < n; i++) {
                this.keeper.random();
            }
        }

        public void debug() {
            System.out.printf("%d %.2f %d\n", this.n, Math.log(this.n) / Math.log(2), this.keeper.maxbits);
        }

        public static void main(String[] args) {
            for (int i = 1000; i < 100000; i += 100) {
                Experiment exp = new Experiment(i);
                exp.work();
                exp.debug();
            }
        }
    }
}
