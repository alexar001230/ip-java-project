package com.yunlong.lee.algo.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 322. 零钱兑换
 * @date 19/4/23 7:22 下午
 * @link https://leetcode.cn/problems/coin-change/
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        return doCoinChange(coins, amount);
    }

    private int doCoinChange(int[] coins, int amount) {
        //为什么可以使用dp? 1.要达到一个目标,2.目标迭代过程中每一步都依赖前一步的计算结果，
        // 3.而初始结果是可求的，4.当前步骤与前一步存在转换关系
        // 对于此题，1.要达到目标结果 值为amount  2.在达到amount之前，
        // dp[amount] = min(amount-coin[j])+1  j在coins面值范围内
        //dp 5步走
        //1.确定 dp[i]含义，达到面值为i的最少硬币数为dp[i]个
        //2.转移方程，dp[i] = min(i-coin[j])+1; min(i-coin[j])这部分可看做dp[i-coin[j]]
        //3.遍历顺序,i range 0~11
        //4.初始化，dp[0] = 0;dp[1] = 1; dp[2] = ,dp[3]
        //5.举例验证

        HashMap<Integer, Integer> valueMap = new HashMap<Integer, Integer>();
        for (Integer value : coins) {
            valueMap.put(value, 1);
        }

        int max = amount + 1;

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        // dp[1] = Objects.isNull(valueMap.get(1)) ? 0 : 1;
        // dp[2] = Objects.isNull(valueMap.get(2)) ? dp[1] + 1 : 1;
        for (int i = 1; i <= amount; i++) {
            int preMinCoins = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                // min(dp[i - coins[j]])+1
                if (i - coins[j] < 0) {
                    // dp[i - coins[j]] = 0;
                } else {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
            // dp[i] = preMinCoins + 1;
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        System.out.println(new CoinChange().coinChange(coins, amount));
    }
}
