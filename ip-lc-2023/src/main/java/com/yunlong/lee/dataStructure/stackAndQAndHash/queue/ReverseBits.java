package com.yunlong.lee.dataStructure.stackAndQAndHash.queue;

import java.util.LinkedList;

/**
 * @author lijie
 * @version 1.0
 * @description 190. 颠倒二进制位
 * @date 7/5/23 1:57 上午
 * @link https://leetcode.cn/problems/reverse-bits/
 */
public class ReverseBits {
    public long reverseBits(long n) {
        return doReverseBits(n);
    }

    //region 移位思想
    private int doReverseBitsByBit(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = res << 1;
            res = res + (n & 1);//取二进制右边第一位
            n = n >> 1;

        }
        return res;
    }
    //endregion

    //region 先将n处理成二进制,再从二进制中倒排复原新的十进制数
    private LinkedList<Long> stack = new LinkedList<>();

    private long doReverseBits(long n) {
        while (n != 0) {
            stack.push(n % 2);
            n = n / 2;
        }
        while (stack.size() < 32) {
            stack.push(0L);
        }

        long base = 1;
        long res = 0;
        while (!stack.isEmpty()) {
            long aNo = stack.pop();
            if (aNo == 1) {
                res = base * aNo + res;
            }
            base = base * 2;
        }
        return res;
    }
    //endregion

    public static void main(String[] args) {
        long n = 4294967293L;
        System.out.println(new ReverseBits().reverseBits(n));
    }
}
