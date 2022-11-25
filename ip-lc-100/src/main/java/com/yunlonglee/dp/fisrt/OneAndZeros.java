package com.yunlonglee.dp.fisrt;

import com.alibaba.fastjson.JSON;

/**
 * @author lijie
 * @version 1.0
 * @description 474. 一和零
 * @date 21/2/22 8:39 下午
 */
public class OneAndZeros {
    public int findMaxForm(String[] strs, int m, int n) {
        //dp[m][i][j],取第m字串，含有i个0，j个1  strs子集最大长度
        //转移方程 dp[m][i][j] = max(dp[m-1][i][j],dp[m-1][i-zeros][j-ones]+1)
        //遍历顺序  沿着字符串遍历
        //初始化  dp[0][i][j] = 0
        //举例推导
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int i = 1; i <= strs.length; i++) {
            int[] zeroOneNums = getZeroOneNums(strs[i - 1]);
            int zeroNums = zeroOneNums[0];
            int oneNums = zeroOneNums[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= zeroNums && k >= oneNums) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k],
                                dp[i - 1][j - zeroNums][k - oneNums] + 1);
                    }
                }
            }
        }
        return dp[strs.length][m][n];

    }

    private static int[] getZeroOneNums(String str) {
        int[] arr = new int[2];
        String aStr = str;
        String zeros = aStr.replace("1", "");
        arr[0] += zeros.length();
        String ones = aStr.replace("0", "");
        arr[1] += ones.length();
        return arr;
    }

    public static void main(String[] args) {
        String s = "0000111100";
        System.out.println(JSON.toJSONString(getZeroOneNums(s)));
    }
}
