package com.yunlonglee.dp.fisrt;

/**
 * @author lijie
 * @version 1.0
 * @description 1049. 最后一块石头的重量 II
 * @date 18/2/22 9:05 下午
 */
public class LastStoneWeight2 {
    //转换成01背包问题dp[j] = max[dp[j],dp[j-weight[i]]+value[i]],处理成两个堆的石头(接近或相同),
    // target = sum/2 计算dp[target],最终返回值=sum-dp[target]-dp[target]
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        //先遍历物品
        for (int i = 0; i < stones.length; i++) {
            //再遍历背包,且从大到小
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - dp[target] - dp[target];
    }
}
