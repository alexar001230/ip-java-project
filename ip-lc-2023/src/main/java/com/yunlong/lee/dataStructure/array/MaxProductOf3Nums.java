package com.yunlong.lee.dataStructure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 给定数组找出最大三个数的乘积
 * @date 31/3/23 4:02 下午
 * @link https://leetcode.cn/problems/maximum-product-of-three-numbers/
 */
public class MaxProductOf3Nums {
    public int maximumProduct(int[] nums) {
        // backTracking(nums, 3, 0);
        // return maxProduct(triples);
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[nums.length - 1],
                nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]);
    }

    List<List<Integer>> triples = new ArrayList<>();

    private int maxProduct(List<List<Integer>> triples) {
        int max = Integer.MIN_VALUE;
        for (List<Integer> triple : triples) {
            int curProduct = triple.get(0) * triple.get(1) * triple.get(2);
            if (max < curProduct) {
                max = curProduct;
            }
        }
        return max;
    }


    LinkedList<Integer> aTriple = new LinkedList<>();

    private void backTracking(int[] nums, int k/** 回溯树深度 **/,
                              int backTrackStartIdx /** 数组下标 **/) {
        //回溯三步走
        //1.确定回溯函数参数及返回值
        //2.确定终止条件,存放结果
        if (aTriple.size() == k) {
            triples.add(new ArrayList<>(aTriple));
            return;
        }
        //3.回溯函数遍历搜索
        for (int i = backTrackStartIdx; i < nums.length - (k - aTriple.size()) + 1; i++) {
            aTriple.add(nums[i]);
            backTracking(nums, k, i + 1);
            aTriple.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-100, -98, -1, 2, 3, 4};
        System.out.println(new MaxProductOf3Nums().maximumProduct(nums));
    }
}
