package com.yunlonglee.dp;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 24/1/22 11:01 下午
 */
public class MinCostClimbingStairs {
    public static void main(String[] args) {
        int[] cost = new int[]{10,15,20};
        System.out.println(minCostClimbingStairs(cost));
    }


    public static int minCostClimbingStairs(int[] cost) {
        // 1.dpArr[i]  i表示cost[i]下标  dpArr[i]表示从0阶到i阶的最小花费
        // 2.确定递推公式
        // dpArr[i] = min(dpArr[i-1]+cost[i-1],dpArr[i-2]+cost[i-2]);
        // 3.dp数组初始化
        // dp[0] = cost[0];
        // dp[1] = min(cost[1],cost[0])
        // dp[2] = min(dpArr[1]+cost[1],dpArr[0]+cost[0])
        // 4.确定遍历顺序
        // 5.举例推到dp数组
        int n = cost.length;
        int[] dpArr = new int[n+1];
        dpArr[0] = 0;
        dpArr[1] = 0;
        dpArr[2] = Math.min(dpArr[1]+cost[1],dpArr[0]+cost[0]);

        for(int i =3;i<=cost.length;i++){
            dpArr[i] = Math.min(dpArr[i-1]+cost[i-1],dpArr[i-2]+cost[i-2]);
        }
        return dpArr[n];
    }
}
