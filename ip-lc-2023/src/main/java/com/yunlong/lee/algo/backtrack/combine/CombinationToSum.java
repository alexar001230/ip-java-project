package com.yunlong.lee.algo.backtrack.combine;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 3/3/23 7:34 下午
 * @link https://leetcode.cn/problems/combination-sum/
 */
public class CombinationToSum {
    //存放结果集
    List result = new ArrayList<List<Integer>>();
    List aRes = new ArrayList<Integer>();


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return doCombinationSum(candidates, target);
    }

    public List<List<Integer>> doCombinationSum(int[] candidates, int target) {
        int[] sortedArr = Arrays.stream(candidates).sorted().toArray();
        backTracking(sortedArr, target, 0, 0);
        return result;
    }
    public void backTracking(
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
                backTracking(candidates, target, sum + candidates[i], i );
                aRes.remove(aRes.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{5, 10, 8, 4, 3, 12, 9};
        int target = 27;
        System.out.println(JSON.toJSONString(new CombinationToSum().combinationSum(candidates, target)));
    }
}
