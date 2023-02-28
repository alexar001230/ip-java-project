package com.yunlong.lee.array.nsum;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 3/2/23 1:26 下午
 * @link
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1, 5};
        int target = 6;
        System.out.println(JSON.toJSONString(twoSum(nums, target)));

    }

    /**
     * 1.构建map为数组中的值和value
     * 2.构建存值的时候(遍历过程)，先检查到目标值剩余的量是否存在，如果存在则返回，否则继续记录key-value值
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] resArr = new int[2];
        HashMap<Integer, Integer> num2IdxMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //寻找之前先检查
            if (num2IdxMap.containsKey(target - nums[i])) {
                resArr[0] = num2IdxMap.get(target - nums[i]);
                resArr[1] = i;
                return resArr;
            }
            num2IdxMap.put(nums[i], i);
        }
        return resArr;
    }
}
