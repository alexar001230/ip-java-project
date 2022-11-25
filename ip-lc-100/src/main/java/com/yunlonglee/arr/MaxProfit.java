package com.yunlonglee.arr;

/**
 * @author lijie
 * @version 1.0
 * @description 121. 买卖股票的最佳时机
 * @date 9/3/22 2:12 下午
 */
public class MaxProfit {
    public int maxProfit(int[] prices) {
        //动态规划5步曲
        //1.确定dp数组及下标含义，dp[i]表示第i天获取的最高收益
        //2.状态转移方程,dp[i] = max(dp[i-1],prices[i]-min(prices[0~i-1]))
        //3.确定遍历顺序,遍历prices
        //4.初始化
        //5.举例推导
        if (null == prices || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        int[] dp = new int[prices.length + 1];
        dp[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = Math.max(dp[i - 1], prices[i] - min);
            if (min > prices[i]) {
                min = prices[i];
            }
        }
        int maxProfit = 0;
        for (int i = 0; i < dp.length; i++) {
            if (maxProfit < dp[i]) {
                maxProfit = dp[i];
            }
        }
        return maxProfit;
    }


    /**
     * 裁判机上会超时
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i] && maxProfit < (prices[j] - prices[i])) {
                    maxProfit = prices[j] - prices[i];
                }
            }
        }
        return maxProfit;
    }
}
