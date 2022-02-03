package com.yunlonglee.hash;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 17/1/22 11:12 下午
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] arr = new int[]{3,2,4};
        System.out.println(JSON.toJSONString(twoSum(arr,6)));
    }
    public static int[] twoSum(int[] nums, int target) {
        if(null == nums || nums.length ==0){
            return new int[0];
        }
        int[] result = new int[2];
        Map<Integer,Integer> num2IndexMap = new HashMap<>();

        for(int i=0;i<nums.length;i++){
            int left = target - nums[i];
            if(num2IndexMap.containsKey(left)){
                result[0] = num2IndexMap.get(left);
                result[1] = i;
                return result;
            }
            num2IndexMap.put(nums[i],i);
        }
        return result;
    }
}
