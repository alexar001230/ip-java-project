package com.yunlong.lee.dataStructure.array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author lijie
 * @version 1.0
 * @description 128. 最长连续序列
 * @date 12/5/23 6:39 下午
 * @link https://leetcode.cn/problems/longest-consecutive-sequence/description/
 */
public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        return doLongestConsecutive(nums);
    }

    private int doLongestConsecutive(int[] nums) {
        HashSet<Integer> noSet = new HashSet<>();
        for (Integer aNo : nums) {
            noSet.add(aNo);
        }
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            int curCnt = 0;
            int curNo = nums[i];
            if (!noSet.contains(curNo - 1)) {
                while (true) {
                    if (noSet.contains(curNo)) {
                        curCnt++;
                        curNo++;
                    } else {
                        maxLen = Math.max(curCnt, maxLen);
                        break;
                    }
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        System.out.println(new LongestConsecutive().longestConsecutive(nums));
    }
}
