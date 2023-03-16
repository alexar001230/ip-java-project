package com.yunlong.lee.dataStructure.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijie
 * @version 1.0
 * @description 多数元素
 * @date 16/3/23 8:01 下午
 * @link https://leetcode.cn/problems/majority-element/
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> no2TimesMap = new HashMap<>();
        for (int curNo : nums) {
            if (null == no2TimesMap.get(curNo)) {
                no2TimesMap.put(curNo, 1);
            } else {
                no2TimesMap.put(curNo, no2TimesMap.get(curNo) + 1);
            }
        }
        Map<Integer, Integer> maxTimes2NoMap = new HashMap<>();
        int maxTimes = 0;
        for (Map.Entry entry : no2TimesMap.entrySet()) {
            int no = (int) entry.getKey();
            int times = (int) entry.getValue();
            if (times > maxTimes) {
                maxTimes = times;
                maxTimes2NoMap.put(maxTimes, no);
            }
        }
        return maxTimes2NoMap.get(maxTimes);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,1,1,1,2,2};
        System.out.println(new MajorityElement().majorityElement(nums));
    }
}
