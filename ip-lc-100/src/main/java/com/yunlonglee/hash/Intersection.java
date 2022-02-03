package com.yunlonglee.hash;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lijie
 * @version 1.0
 * @description 两个数组求交集
 * @date 3/2/22 3:44 下午
 */
public class Intersection {
    public static int[] intersection(int[] nums1, int[] nums2) {
        if (null == nums1 || null == nums2 || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> resSet = new HashSet<>();

        for(int i:nums1){
            set1.add(i);
        }

        for(int i:nums2){
            if(set1.contains(i)){
                resSet.add(i);
            }
        }

        int[] resArr = new int[resSet.size()];
        int index = 0;
        for(int i:resSet){
            resArr[index++] = i;
        }
        return resArr;
    }

    public static void main(String[] args) {
        int[] num1 = new int[]{1, 2, 2, 1};
        int[] num2 = new int[]{2, 2};
        System.out.println(JSON.toJSONString(intersection(num1, num2)));

    }

}
