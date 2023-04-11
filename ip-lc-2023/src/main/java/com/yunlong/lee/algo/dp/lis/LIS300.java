package com.yunlong.lee.algo.dp.lis;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author lijie
 * @version 1.0
 * @description 300. 最长递增子序列
 * @date 11/4/23 12:34 下午
 * @link https://leetcode.cn/problems/longest-increasing-subsequence/
 */
public class LIS300 {
    public int lengthOfLIS(int[] nums) {
        return doLengthOfLis(nums);
    }

    private int doLengthOfLis(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //dp 5步走
        //1.dp[i]含义,某下标到i下标,最长升序列长度(含nums[i])
        int[] dp = new int[nums.length];
        //2.转移方程,dp[i] = max(dp[j])+1  j<i && nums[j] < nums[i]
        //3.初始化  dp[0] = 1,
        dp[0] = 1;
        //4.遍历,外层i range 0--> size -1,内层推算max(dp[j]),j range 0--> i
        int maxLen = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);

        }
        //5.举例验证
        return maxLen;
    }


    //region 利用map记录前置最长升序列串思路
    //idx到最长列表集合map
    private HashMap<Integer, LinkedList<LinkedList<Integer>>> idx2LisMap = new HashMap<>();

    private int myDoLengthOfLis(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //dp 5步走
        //1.dp[i,j]含义，下标i到j最长单调增序列长度
        //2.转移方程，dp[i,j] = f(dp[i,j-1],dp[i-1,j]),i到j-1最长序列rem[],
        // 当nums[j]>rem[size-1],dp[i,j] = dp[i,j-1]+1,否则，dp[i,j] = dp[i,j-1]
        // List<List<Integer>> preLIS
        //3.初始化 dp[0] = 1,dp[1] =
        //4.遍历顺序，i range 0 --> size-1
        //5.举例验证
        //region 初始化
        //dp[i]表示从0到i最长子序列的长度
        int[] dp = new int[nums.length];
        //idx到最长列表集合map
        // HashMap<Integer, LinkedList<LinkedList<Integer>>> idx2LisMap = new HashMap<>();
        //到i位置,长度为dp[i],的列表
        LinkedList<LinkedList<Integer>> lisOfI = new LinkedList<>();
        dp[0] = 1;
        LinkedList<Integer> list = new LinkedList<>();
        list.add(nums[0]);
        lisOfI.add(list);
        idx2LisMap.put(0, lisOfI);
        //endregion
        LinkedList<Integer> minTailOfPreILis = list;
        for (int i = 1; i < nums.length; i++) {
            // int curNo = nums[i];
            // int minTailOfPreI =
            //         minTailOfPreILis.get(minTailOfPreILis.size() - 1);
            // if (curNo > minTailOfPreI) {
            //     dp[i] = dp[i - 1] + 1;
            //     minTailOfPreILis.add(curNo);
            // } else {
            //     dp[i] = dp[i - 1];
            // }
            compareCur2PreLis(i, nums);
            dp[i] = idx2LisMap.get(i).get(0).size();
        }
        return dp[nums.length - 1];
    }

    private void compareCur2PreLis(int curIdx, int[] nums) {
        int curNo = nums[curIdx];
        LinkedList<LinkedList<Integer>> lisOfCurIdx = new LinkedList<>();
        int maxLen = -1;
        for (LinkedList<Integer> list : idx2LisMap.get(curIdx - 1)) {
            if (list.get(list.size() - 1) < curNo) {
                list.add(curNo);
                LinkedList<Integer> aCurLIS = new LinkedList<>(list);
                lisOfCurIdx.add(aCurLIS);
                maxLen = aCurLIS.size();
                list.remove(list.size() - 1);
            } else {
                LinkedList<Integer> aCurLIS = new LinkedList<>(list);
                lisOfCurIdx.add(aCurLIS);
            }
        }
        boolean addedCurNo = false;
        for (int i = 0; i < lisOfCurIdx.size(); ) {
            if (maxLen > 0) {
                if (lisOfCurIdx.get(i).size() != maxLen) {
                    lisOfCurIdx.remove(i);
                } else {
                    i++;
                }
            } else {
                if (!addedCurNo && lisOfCurIdx.get(i).size() == 1) {
                    LinkedList<Integer> newList = new LinkedList<>();
                    newList.add(nums[curIdx]);
                    lisOfCurIdx.add(newList);
                    addedCurNo = true;
                }
                i++;
            }
        }
        if (lisOfCurIdx.size() > 0) {
            idx2LisMap.put(curIdx, lisOfCurIdx);
        }
        return;
    }
    //endregion


//     10,9,2,5,3,7,101,18
// 10  1  1 1 2 2 3  4   4
// 9   n  1 1 2 2 3  4   4
// 2   n  n 1 2 2 3  4   4
// 5   n  n n 1 1 2  3   3
// 3            1 2  3   3
// 7              1  2   2
// 101               1   1
// 18                    1

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(new LIS300().lengthOfLIS(nums));
    }
}