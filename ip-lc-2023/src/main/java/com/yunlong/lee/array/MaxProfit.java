package com.yunlong.lee.array;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 10/3/23 2:12 下午
 * @link https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 */
public class MaxProfit {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (minPrice > prices[i]) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        return maxProfit;

    }

    //双层循环会超时
    public int myMaxProfit(int[] prices) {
        if (null == prices || prices.length < 2) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max(max, prices[j] - prices[i]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7, 6, 4, 3, 1};
        System.out.println(new MaxProfit().maxProfit(prices));
    }
}
