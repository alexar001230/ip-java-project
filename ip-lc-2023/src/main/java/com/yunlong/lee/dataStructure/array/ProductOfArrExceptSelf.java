package com.yunlong.lee.dataStructure.array;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 238. 除自身以外数组的乘积
 * @date 10/4/23 10:17 上午
 * @link https://leetcode.cn/problems/product-of-array-except-self/
 */
public class ProductOfArrExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        return doProductExceptSelf(nums);
    }

    private int[] doProductExceptSelf(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];

        HashMap<Integer, Integer> normalSeqIdx2ChengjiMap = new HashMap<>();
        HashMap<Integer, Integer> reverseSeqIdx2ChengjiMap = new HashMap<>();

        int i = 0;
        int j = len - 1;
        while (i < len - 1 && j > 0) {
            //自然序乘积存入map
            Integer normalPre = normalSeqIdx2ChengjiMap.get(i - 1);
            if (Objects.nonNull(normalPre)) {
                int curNormalChengji = nums[i] * normalSeqIdx2ChengjiMap.get(i - 1);
                normalSeqIdx2ChengjiMap.put(i, curNormalChengji);
            } else {
                normalSeqIdx2ChengjiMap.put(i, nums[i]);
            }
            i++;

            //逆序乘积存入map
            Integer reversePost = reverseSeqIdx2ChengjiMap.get(j + 1);
            if (Objects.nonNull(reversePost)) {
                int curReverseChengji =
                        nums[j] * reverseSeqIdx2ChengjiMap.get(j + 1);
                reverseSeqIdx2ChengjiMap.put(j, curReverseChengji);
            } else {
                reverseSeqIdx2ChengjiMap.put(j, nums[j]);
            }
            j--;
        }
        for (int k = 0; k <= len - 1; k++) {
            if (k == 0) {
                result[k] = reverseSeqIdx2ChengjiMap.get(k + 1);
                continue;
            }
            if (k == len - 1) {
                result[k] = normalSeqIdx2ChengjiMap.get(k - 1);
                continue;
            }
            result[k] =
                    normalSeqIdx2ChengjiMap.get(k - 1) * reverseSeqIdx2ChengjiMap.get(k + 1);

        }
        return result;
    }

    // 0-->len-1   0 a0,1 a01,2 a012
    // len-1 --> 0 3 a3,2 a32,1 a321
    //
    // a123,a023,a013,a012

    public static void main(String[] args) {
        // int[] nums = new int[]{1, 2, 3, 4};
        // int[] nums = new int[]{-1,1,0,-3,3};
        int[] nums = new int[]{0, 0};
        System.out.println(JSON.toJSONString(new ProductOfArrExceptSelf().productExceptSelf(nums)));
    }
}
