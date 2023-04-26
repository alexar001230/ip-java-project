package com.yunlong.lee.algo.backtrack.combine;

import com.alibaba.fastjson.JSON;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 413. 等差数列划分
 * @date 26/4/23 4:41 下午
 * @link https://leetcode.cn/problems/arithmetic-slices/
 */
public class ArithmeticSlices {
    public int numberOfArithmeticSlices(int[] nums) {
        return doNumberOfArithmeticSlices(nums);
    }

    //region 1.先求所有Cnk列表  2.列表中筛选连续等差数列  算法n3，oj判定超时
    private int doNumberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        //1.算出所有Cnk列表
        LinkedList<LinkedList<Pair<Integer, Integer>>> allSlices = new LinkedList<>();
        for (int i = 3; i <= nums.length; i++) {
            LinkedList<LinkedList<Pair<Integer, Integer>>> aKPaths = new LinkedList<>();
            backTrackingCnk(nums, i, 0, aKPaths);
            allSlices.addAll(aKPaths);
        }
        int cnt = 0;
        LinkedList<LinkedList<Pair<Integer, Integer>>> allCommonDiffSlices =
                new LinkedList<>();
        //2.筛选出是等差数列的列表
        for (LinkedList<Pair<Integer, Integer>> aSlice : allSlices) {
            int valueDiff = aSlice.get(1).getKey() - aSlice.get(0).getKey();
            // int idxDiff = aSlice.get(1).getValue() - aSlice.get(0).getValue();
            int idxDiff = 1;
            boolean isCommonDiff = true;
            for (int i = 1; i < aSlice.size(); i++) {
                int curValDiff =
                        aSlice.get(i).getKey() - aSlice.get(i - 1).getKey();
                int curIdxDiff =
                        aSlice.get(i).getValue() - aSlice.get(i - 1).getValue();
                if (curValDiff != valueDiff || curIdxDiff != idxDiff) {
                    isCommonDiff = false;
                    break;
                }
            }
            if (isCommonDiff) {
                allCommonDiffSlices.add(aSlice);
                cnt++;
            }
        }
        System.out.println(JSON.toJSONString(allCommonDiffSlices));
        return cnt;
    }

    private LinkedList<Pair<Integer, Integer>> aPath = new LinkedList<>();


    //回溯3步走，1.确定出入参
    private void backTrackingCnk(int[] nums, int k, int startIdx,
                                 LinkedList<LinkedList<Pair<Integer, Integer>>> aKPaths) {
        //2.递归出口
        if (aPath.size() == k) {
            aKPaths.add(new LinkedList<>(aPath));
            return;
        }
        //3.回溯单层遍历方法
        for (int i = startIdx; i < nums.length - (k - aPath.size()) + 1; i++) {
            aPath.add(new Pair<>(nums[i], i));
            backTrackingCnk(nums, k, i + 1, aKPaths);
            aPath.removeLast();
        }
    }
    //endregion

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        int k = 3;
        System.out.println(new ArithmeticSlices().numberOfArithmeticSlices(nums));
    }
}
