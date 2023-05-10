package com.yunlong.lee.dataStructure.array;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author lijie
 * @version 1.0
 * @description 31. 下一个排列
 * @date 9/5/23 3:35 下午
 * @link https://leetcode.cn/problems/next-permutation/
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        doNextPermutationByTwoPointers(nums);
    }

    //region 从后向前找,1.先找较小的数 2.从后向前，在较小的数的idx右边找较大的数，3.交换 4.idx右边重排序
    private void doNextPermutationByTwoPointers(int[] nums) {
        int smallerIdx = -1;
        for (int i = nums.length - 1; i >= 1; i--) {
            if (nums[i] > nums[i - 1]) {
                smallerIdx = i - 1;
                break;
            }
        }
        int biggerIdx = -1;
        for (int i = nums.length - 1;smallerIdx >=0 && i >= smallerIdx + 1; i--) {
            if (nums[smallerIdx] < nums[i]) {
                biggerIdx = i;
                break;
            }
        }

        if (smallerIdx >= 0 && biggerIdx >= 0) {
            int tmp = nums[smallerIdx];
            nums[smallerIdx] = nums[biggerIdx];
            nums[biggerIdx] = tmp;
            sortPartArr(nums, smallerIdx + 1, nums.length - 1);
        } else {
            sortPartArr(nums, 0, nums.length - 1);
        }
    }

    private void sortPartArr(int[] arr, int fromIdx, int endIdx) {
        int low = fromIdx;
        int high = endIdx;
        while (low < high) {
            int tmp = arr[low];
            arr[low] = arr[high];
            arr[high] = tmp;
            low++;
            high--;
        }
    }

    //endregion

    //region 分析的一坨shit
    private void doNextPermutation(int[] nums) {
        Integer[] arr = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };
        Arrays.sort(arr, comparator);
        boolean hasFind = false;
        for (int i = 0; i < nums.length; i++) {
            if (hasFind) {
                break;
            }
            if (arr[i] != nums[i]) {
                int preIdx = i;
                int j = i + 1;
                while (j < nums.length) {
                    if (nums[j] == arr[i]) {
                        int tmp = nums[preIdx];
                        nums[preIdx] = nums[j];
                        nums[j] = tmp;
                        hasFind = true;
                        break;
                    }
                    j++;
                    preIdx++;
                }
            }
        }
        if (hasFind) {
            System.out.println(JSON.toJSONString(nums));
            return;
        } else {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = arr[nums.length - 1 - i];
            }
            System.out.println(JSON.toJSONString(nums));
            return;
        }
    }
    //endregion

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1};
        new NextPermutation().nextPermutation(nums);
    }
}
