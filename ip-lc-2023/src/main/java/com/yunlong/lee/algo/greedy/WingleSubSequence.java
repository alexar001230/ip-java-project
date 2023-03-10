package com.yunlong.lee.algo.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 9/2/23 1:47 下午
 * @link https://leetcode.cn/problems/wiggle-subsequence/
 * [1,17,5,10,13,15,10,5,16,8]
 * [16,-12,5,3,2,-5,-5,11,-8]
 */
public class WingleSubSequence {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 3, 3, 2, 5};
        System.out.println(wiggleMaxLength(nums));
    }


    public static int wiggleMaxLength(int[] nums) {
        //数据分列上下坡角度看,类似双游标指针遍历数组，两个游标之间产生的差值diff来回摆动，cnt++,前置diff继承当前diff的值
        int preDiff = 0;
        int curDiff = 0;
        int cnt = 1;
        for (int i = 1; i < nums.length; i++) {
            curDiff = nums[i] - nums[i - 1];
            //=0保证平坡只算一次
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                cnt++;
                preDiff = curDiff;
            }

        }
        return cnt;
    }

    public static int wiggleMaxLengthMy(int[] nums) {
        //1.求元素差值列表
        //2.遍历差值列表，统计摇摆数
        Integer[] numsInt = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        List<Integer> numsList =
                Arrays.stream(numsInt).collect(Collectors.toList());
        List<Integer> chazhiList = new ArrayList<>();
        for (int i = 0; i < numsList.size() - 1; i++) {
            for (int j = i + 1; j < numsList.size(); j++) {
                chazhiList.add(numsList.get(j) - numsList.get(i));
                break;
            }
        }
        //摇摆标记
        int tag = -1;
        //总长
        int total = 1;
        int chengji = 1 * chazhiList.get(0);
        List<Integer> winggles = new ArrayList<>();
        for (int i = 1; i < chazhiList.size(); i++) {
            int val = chazhiList.get(i);
            if (chengji > 0) {
                if (val < 0) {
                    winggles.add(val);
                    chengji = chengji * val;
                    total++;
                }
            }
            if (chengji < 0) {
                if (val > 0) {
                    winggles.add(val);
                    chengji = chengji * val;
                    total++;
                }
            }
        }
        return total + 1;
    }
}
