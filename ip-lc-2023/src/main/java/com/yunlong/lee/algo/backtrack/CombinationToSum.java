package com.yunlong.lee.algo.backtrack;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 3/3/23 7:34 下午
 * @link https://leetcode.cn/problems/combination-sum/
 */
public class CombinationToSum {
    //存放结果集
    static List result = new ArrayList<List<Integer>>();
    static List aRes = new ArrayList<Integer>();
    static int aSum = 0;

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // int minOfArr = Arrays.stream(candidates).min().getAsInt();
        // int backTrackTreeHeight = target / minOfArr;
        int[] sortedArr = Arrays.stream(candidates).sorted().toArray();
        backTracking(sortedArr, target, 0, 0);
        return result;
    }

    public static void backTracking(
            //1.确定参数
            int[] candidates, int target, int sum, int startIdx) {
        //2.确定回溯终止条件
        if (sum == target) {
            result.add(new ArrayList<>(aRes));
            return;
        }
        //3.确定单层遍历逻辑
        for (int i = startIdx; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            } else {
                aRes.add(candidates[i]);
                // aSum += candidates[i];
                backTracking(candidates, target, sum + candidates[i], i);
                // aSum -= candidates[i];
                aRes.remove(aRes.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{2, 5, 3};
        int target = 7;
        System.out.println(JSON.toJSONString(combinationSum(candidates, target)));
    }
}
