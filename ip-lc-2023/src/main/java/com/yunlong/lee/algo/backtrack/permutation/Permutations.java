package com.yunlong.lee.algo.backtrack.permutation;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 46. 全排列
 * @date 20/4/23 4:04 下午
 * @link https://leetcode.cn/problems/permutations/
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums, int k) {
        return doPermute(nums, k);
    }

    private List<List<Integer>> doPermute(int[] nums, int k) {
        LinkedList<Integer> numList = new LinkedList<>();
        idxUsedArr = new boolean[nums.length];
        for (int aNum : nums) {
            numList.add(aNum);
        }
        backTracking(numList, k);
        return res;
    }

    private List<List<Integer>> res = new LinkedList<>();
    private LinkedList<Integer> aPath = new LinkedList<>();
    private boolean[] idxUsedArr;

    private void backTracking(LinkedList<Integer> numList, int k) {
        //1.确定参数及返回值
        //回溯三步
        //2.找回溯出口
        if (aPath.size() == k) {
            res.add(new LinkedList<>(aPath));
            return;
        }
        //3.回溯算法单层循环搜索遍历
        for (int i = 0; i < numList.size(); i++) {
            if (idxUsedArr[i]) {
                continue;
            }
            aPath.add(numList.get(i));
            idxUsedArr[i] = true;
            backTracking(numList, k);
            aPath.removeLast();
            idxUsedArr[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 2, 3};
        int k = 3;
        System.out.println(new Permutations().permute(nums, k));
    }
}
