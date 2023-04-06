package com.yunlong.lee.dataStructure.array.nsum;

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
    /**
     * 1.构建map为数组中的值和value
     * 2.构建存值的时候(遍历过程)，先检查到目标值剩余的量是否存在，如果存在则返回，否则继续记录key-value值
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumStatic(int[] nums, int target) {
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

    public int[] twoSum(int[] nums, int target) {
        return doTwoSum(nums, target);
    }

    private int[] doTwoSum(int[] nums, int target) {
        if (Objects.isNull(nums) || nums.length == 1) {
            return null;
        }

        int[] resArr = new int[2];
        //num到下标集合的映射,可能存在重复,所以要集合
        HashMap<Integer, LinkedList<Integer>> num2IdxsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            LinkedList<Integer> idxs = num2IdxsMap.get(nums[i]);
            if (Objects.isNull(idxs)) {
                idxs = new LinkedList<>();
            }
            idxs.add(i);
            num2IdxsMap.put(nums[i], idxs);
        }
        for (Integer num : nums) {
            if (num2IdxsMap.containsKey(target - num)) {
                LinkedList<Integer> firstIdxs = num2IdxsMap.get(num);
                LinkedList<Integer> secondIdxs = num2IdxsMap.get(target - num);
                if (secondIdxs.size() >= 1) {
                    resArr[0] = firstIdxs.poll();
                    if (secondIdxs.size() >= 1) {
                        resArr[1] = secondIdxs.poll();
                        break;
                    }
                }
            }
        }
        return resArr;
    }

    public static void main(String[] args) {
        // int[] nums = new int[]{2,7,11,15};
        int[] nums = new int[]{3, 2, 4};
        int target = 6;
        System.out.println(JSON.toJSONString(new TwoSum().twoSum(nums,
                target)));
    }
}
