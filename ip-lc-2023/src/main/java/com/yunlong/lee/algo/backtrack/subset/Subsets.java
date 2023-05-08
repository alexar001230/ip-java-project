package com.yunlong.lee.algo.backtrack.subset;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 78. 子集
 * @date 6/5/23 7:56 下午
 * @link https://leetcode.cn/problems/subsets/
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        return doSubsets(nums);
    }


    private LinkedList<List<Integer>> res = new LinkedList<>();
    private LinkedList<Integer> aPath = new LinkedList<>();

    private List<List<Integer>> doSubsets(int[] nums) {
        res.add(Collections.emptyList());
        for (int k = 1; k <= nums.length; k++) {
            backTrackingCnk(nums, 0, k);
        }
        return res;
    }

    private void backTrackingCnk(int[] nums, int startIdx, int k) {
        if (aPath.size() == k) {
            res.add(new LinkedList<>(aPath));
            return;
        }
        for (int i = startIdx; i < nums.length - (k - aPath.size()) + 1; i++) {
            aPath.add(nums[i]);
            backTrackingCnk(nums, i + 1, k);
            aPath.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(new Subsets().subsets(nums));
    }
}
