package com.yunlonglee.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijie
 * @version 1.0
 * @description 169. 多数元素
 * @date 9/3/22 3:22 上午
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = -1;
        for (int i : nums) {
            int quantity = map.getOrDefault(i, 0) + 1;
            if(quantity > nums.length/2){
                result = i;
                break;
            }
            map.put(i, quantity);
        }
        return result;
    }
}
