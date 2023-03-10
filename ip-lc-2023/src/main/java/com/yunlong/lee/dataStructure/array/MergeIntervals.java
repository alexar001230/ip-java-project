package com.yunlong.lee.dataStructure.array;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 7/3/23 1:42 下午
 * @link https://leetcode.cn/problems/merge-intervals/
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        //排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        List<List<Integer>> mergedLists = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            //当前左右边界
            int curRangeLeft = intervals[i][0];
            int curRangeRight = intervals[i][1];
            //数组为空直接添加
            if (mergedLists.size() == 0) {
                List<Integer> aMergedList = new ArrayList<>();
                Collections.addAll(aMergedList, curRangeLeft, curRangeRight);
                mergedLists.add(aMergedList);
            } else {
                //因为排好序,所以只需要关注最后一个数组就可
                List<Integer> lastMerged = mergedLists.get(mergedLists.size() - 1);
                //已经合并到列表中的最右边界
                int rangeRightMax = lastMerged.get(1);
                //已经合并的最右边界 < 当前左边界，不存在重叠区  [[1,2],[3,6]]  [4,5]
                if (rangeRightMax < curRangeLeft) {
                    List<Integer> aMergedList = new ArrayList<>();
                    Collections.addAll(aMergedList, curRangeLeft, curRangeRight);
                    mergedLists.add(aMergedList);
                } else {
                    int toMergedRight = Math.max(lastMerged.get(1), curRangeRight);
                    lastMerged.remove(1);
                    lastMerged.add(toMergedRight);
                }
            }
        }

        int[][] result = new int[mergedLists.size()][2];
        for (int i = 0; i < mergedLists.size(); i++) {
            for (int j = 0; j < mergedLists.get(i).size(); j++) {
                result[i][j] = mergedLists.get(i).get(j);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 4}, {4, 5}};
        System.out.println(JSON.toJSONString(new MergeIntervals().merge(intervals)));
    }
}
