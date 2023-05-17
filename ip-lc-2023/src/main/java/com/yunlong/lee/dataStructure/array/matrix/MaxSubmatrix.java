package com.yunlong.lee.dataStructure.array.matrix;

import com.alibaba.fastjson.JSON;

/**
 * @author lijie
 * @version 1.0
 * @description 面试题 17.24. 最大子矩阵
 * @date 8/5/23 4:33 下午
 * @link https://leetcode.cn/problems/max-submatrix-lcci/description/
 */
public class MaxSubmatrix {
    public int[] getMaxMatrix(int[][] matrix) {
        return doGetMaxMatrix(matrix);
    }

    private int[] doGetMaxMatrix(int[][] matrix) {
        //dp 5
        //1.dp[i][j],(0,0)->(i,j)的sum
        //2.转移方程  dp[i][j] = dp[i-1][j]-dp[i-1][j-1] +dp[i][j-1]
        //3.遍历顺序，从小到大
        //4.初始化
        //5.举例验证
        int[][] dpArr = new int[matrix.length][matrix[0].length];
        dpArr[0][0] = matrix[0][0];
        for (int i = 1; i < matrix[0].length; i++) {
            dpArr[0][i] = dpArr[0][i - 1] + matrix[0][i];
        }

        for (int i = 1; i < matrix.length; i++) {
            dpArr[i][0] = dpArr[i - 1][0] + matrix[i][0];
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                dpArr[i][j] =
                        dpArr[i - 1][j] + dpArr[i][j - 1] - dpArr[i - 1][j - 1] + matrix[i][j];
            }
        }

        int max = matrix[0][0];
        int[] resIdx = new int[]{0, 0, 0, 0};
        int rMax = maxSubArray(matrix[0]);
        int[] col0Arr = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            col0Arr[i] = matrix[0][i];
        }
        int cMax = maxSubArray(col0Arr);
        for (int rEnd = 1; rEnd < matrix.length; rEnd++) {
            for (int cEnd = 1; cEnd < matrix[0].length; cEnd++) {
                int sumRCEnd = dpArr[rEnd][cEnd];
                if (rEnd == 0 || cEnd == 0) {
                    if (cEnd != 0) {
                        if (sumRCEnd > max) {
                            max = sumRCEnd;
                            resIdx = new int[]{0, 0, rEnd, 0};
                        }
                    }
                    if (sumRCEnd > max) {
                        max = sumRCEnd;
                        resIdx = new int[]{0, 0, rEnd, cEnd};
                    }
                    continue;
                }
                for (int rStart = 1; rStart < rEnd; rStart++) {
                    for (int cStart = 1; cStart < cEnd; cStart++) {
                        int max1 = sumRCEnd;
                        int maxR0 = dpArr[0][cEnd];
                        int maxC0 = dpArr[rEnd][0];
                        if (rStart >= 1 && cStart >= 1) {
                            int sumRCStart = dpArr[rStart - 1][cStart - 1];
                            int curSum =
                                    sumRCEnd + sumRCStart - dpArr[rEnd][cStart - 1] - dpArr[rStart - 1][cEnd];
                            System.out.println(curSum);

                            max = Math.max(Math.max(Math.max(Math.max(max, curSum), max1),
                                    maxR0), maxC0);
                            if (max == max1) {
                                resIdx = new int[]{0, 0, rEnd, cEnd};
                            }
                            if (max == maxR0) {
                                resIdx = new int[]{0, 0, 0, cEnd};
                            }

                            if (max == maxC0) {
                                resIdx = new int[]{0, 0, rEnd, 0};
                            }

                            if (max == curSum) {
                                resIdx = new int[]{rStart, cStart, rEnd, cEnd};
                            }

                        } else {
                            if (rStart == 0 && cStart > 0) {
                                int curSum =
                                        dpArr[rEnd][cEnd] - dpArr[0][cStart - 1];
                                if (curSum > max) {
                                    resIdx = new int[]{rStart, cStart, rEnd, cEnd};
                                }
                            }

                            if (rStart > 0 && cStart == 0) {
                                int curSum =
                                        dpArr[rEnd][cEnd] - dpArr[rStart - 1][0];
                                if (curSum > max) {
                                    resIdx = new int[]{rStart, 0, rEnd, cEnd};
                                }
                            }
                        }
                    }
                }
            }
        }
        return resIdx;
    }


    public int maxSubArray(int[] nums) {
        //dp 5步走
        //1.dp[i]含义，到下标i的连续最大子数组和为dp[i]
        //2.转移方程,dp[i] = max(dp[i-1]+nums[i],nums[i]),这里nums[i]可单独成一段
        //3.初始化 dp[0] = nums[0],
        //4.遍历，顺序遍历即可
        //5.举例验证
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int maxRes = nums[0];
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > maxRes) {
                maxRes = dp[i];
            }
        }
        return maxRes;
    }


    public int[] doGetMaxMatrixAns(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        int dp = 0, start = 0;
        int[] ans = new int[]{-1, -1, 200, 200};//结果
        int[] sum = null;//纵向累加数组
        for (int i = 0; i < matrix.length; i++) {
            sum = new int[matrix[0].length];
            for (int j = i; j < matrix.length; j++) {
                dp = 0;
                start = 0;
                for (int k = 0; k < sum.length; k++) {
                    sum[k] += matrix[j][k];
                    dp += sum[k];
                    if (max < dp) {
                        ans[0] = i;
                        ans[1] = start;
                        ans[2] = j;
                        ans[3] = k;
                        max = dp;
                    }
                    if (dp < 0) {
                        dp = 0;
                        start = k + 1;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{-1, 0}, {0, -1}};
        System.out.println(JSON.toJSONString(new MaxSubmatrix().getMaxMatrix(matrix)));
    }
}
