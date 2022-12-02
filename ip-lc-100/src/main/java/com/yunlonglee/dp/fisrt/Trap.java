package com.yunlonglee.dp.fisrt;

/**
 * @author lijie
 * @version 1.0
 * @description 42.接雨水
 * @link https://leetcode.cn/problems/trapping-rain-water/
 * @date 2/12/22 1:37 下午
 */
public class Trap {
    public static void main(String[] args) {
        int[] height = new int[]{4, 2, 0, 3, 2, 5};

        System.out.println(trap(height));
    }

    public static int trap(int[] height) {
        /**
         * 动态规划5步曲
         * 1.确定dp[i]的含义
         *   a.leftMax[i],i位置及i位置左边最大高度max(height[i-1],height[i]),边界值 n-1>=i>=1,
         *   b.right[i],i位置及i位置右边最大高度，max(height[i],height[i+1]),边界值 n-2>=i>=0,
         * 2.找转移方程
         *   i处能接的雨水量volume[i] = min(leftMax[i],rightMax[i])-height[i]
         * 3.遍历顺序
         *   从左到右求leftMax[i],从右到左求rightMax[i]
         *   最后遍历一遍数组,求sum = sum(min[leftMax[i],rightMax[i]-height[i]])
         * 4.初始化
         * 5.举例推导验证
         */
        int arrSize = height.length;

        int[] leftMaxArr = leftMaxArr(height);
        int[] rightMaxArr = rightMaxArr(height);

        int[] volumeArr = volumeArr(leftMaxArr, rightMaxArr, height);

        int total = 0;
        for (int i = 0; i < arrSize; i++) {
            total += volumeArr[i];
        }

        return total;
    }


    private static int[] volumeArr(int[] leftMaxArr, int[] rightMaxArr,
                                   int[] heightArr) {
        int arrSize = heightArr.length;
        int[] volumeArr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            volumeArr[i] = (Math.min(leftMaxArr[i], rightMaxArr[i]) - heightArr[i]);
        }
        return volumeArr;
    }


    private static int[] leftMaxArr(int[] heightArr) {
        int arrSize = heightArr.length;
        int[] leftMaxArr = new int[arrSize];

        leftMaxArr[0] = heightArr[0];
        for (int i = 1; i < arrSize; i++) {
            leftMaxArr[i] = Math.max(leftMaxArr[i - 1], heightArr[i]);
        }
        return leftMaxArr;
    }

    private static int[] rightMaxArr(int[] heightArr) {
        int arrSize = heightArr.length;
        int[] rightMaxArr = new int[arrSize];
        rightMaxArr[arrSize - 1] = heightArr[arrSize - 1];
        for (int i = arrSize - 2; i >= 0; i--) {
            rightMaxArr[i] = Math.max(rightMaxArr[i + 1], heightArr[i]);
        }
        return rightMaxArr;
    }
}
