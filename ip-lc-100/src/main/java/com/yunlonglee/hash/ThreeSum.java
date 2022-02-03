package com.yunlonglee.hash;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lijie
 * @version 1.0
 * @description 三数之和
 * @date 4/2/22 1:48 上午
 */
public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i);
        }
        //正序排序
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        List<List<Integer>> resultList = new ArrayList<>();
        //双/三指针遍历
        for (int i = 0; i < list.size(); i++) {
            //如果第一个大于目标值,则直接返回空列表
            if (list.get(i) > 0) {
                return resultList;
            }
            //最左指针移动到第一个不一样的数值
            if (i > 0 && list.get(i).equals(list.get(i - 1))) {
                continue;
            }
            int left = i;
            int right = list.size() - 1;
            int mid = i + 1;
            while (mid < right) {
                int aSum = list.get(left) + list.get(mid) + list.get(right);
                //小于目标值调节mid指针
                if (aSum < 0) {
                    mid++;
                    continue;
                }
                //等于目标值,调节mid指针/right指针，寻找下一个组合
                if (aSum == 0) {
                    List<Integer> aResult = new ArrayList<>();
                    aResult.add(list.get(left));
                    aResult.add(list.get(mid));
                    aResult.add(list.get(right));
                    resultList.add(aResult);
                    //存在重复值，调节指针到最后一个
                    while (mid < right && list.get(mid).equals(list.get(mid + 1))) {
                        mid++;
                    }
                    while (mid < right && list.get(right).equals(list.get(right - 1))) {
                        right--;
                    }
                    //已经计算过了继续自减/增
                    right--;
                    mid++;
                }
                if (aSum > 0) {
                    right--;
                    continue;
                }
            }
        }
        return resultList;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 0, 0};
        System.out.println(JSON.toJSONString(threeSum(nums)));
    }


    public List<List<Integer>> threeSumShit(int[] nums) {
        //1.两个数的和map
        Map<String, Integer> num2TwoSumMap = new ConcurrentHashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int twoSum = nums[i] + nums[j];
                num2TwoSumMap.put(twoSum + "_" + nums[i] + "_" + nums[j] + "_" + j,
                        twoSum);
            }
        }

        for (Map.Entry<String, Integer> entry : num2TwoSumMap.entrySet()) {
            int twoSum = entry.getValue();
            String key = entry.getKey();
            String[] v = key.split("_");
            int maxIndex = Integer.parseInt(v[v.length - 1]);
            int left = 0 - twoSum;
            for (int i = maxIndex + 1; i < nums.length; i++) {
                if (nums[i] == left) {
                    num2TwoSumMap.put(key + "_" + nums[i], 0);
                }
            }
        }

        List<List<Integer>> resultList = new ArrayList<>();

        HashSet<HashSet<Integer>> resultSet = new HashSet<>();

        for (Map.Entry entry : num2TwoSumMap.entrySet()) {
            int sum = (Integer) entry.getValue();
            if (sum == 0) {
                String key = (String) entry.getKey();
                String[] v = key.split("_");
                if (v.length == 5) {
                    int first = Integer.parseInt(v[1]);
                    int second = Integer.parseInt(v[2]);
                    int third = Integer.parseInt(v[4]);
                    HashSet<Integer> aSet = new HashSet<>();
                    aSet.add(first);
                    aSet.add(second);
                    aSet.add(third);
                    if (!resultSet.contains(aSet)) {
                        resultSet.add(aSet);
                        List<Integer> aResult = new ArrayList<>();
                        aResult.add(first);
                        aResult.add(second);
                        aResult.add(third);
                        resultList.add(aResult);
                    }
                }
            }
        }
        return resultList;
    }


}
