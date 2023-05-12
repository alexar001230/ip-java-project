package com.yunlong.lee.dataStructure.array.interval;

import com.yunlong.lee.utils.arr.ArrUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lijie
 * @version 1.0
 * @description 435. 无重叠区间
 * @date 20/4/23 11:23 上午
 * @link https://leetcode.cn/problems/non-overlapping-intervals/
 */
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        return doEraseOverlapIntervals(intervals);
    }

    //region ans
    private int doEraseOverlapIntervals(int[][] intervals) {
        //dp 5步走，转换问题为：选出最多数量n的区间使其每个区间之间不重复（区间按照左边界排序）
        //1.dp[i],到区间i可最多选出dp[i]个区间，满足区间不重复
        //2.转移方程,dp[i] = max(dp[i],dp[j]+1),其中 j<i,排序后的j区间rj <= li
        //3.遍历顺序,i从0到n-1,j从0到i-1
        //4.初始化，dp[i] = 1,对于每个区间最少都能选出自身一个作为不重复的区间
        //5.举例验证
        if (intervals.length == 0) {
            return 0;
        }

        int[] dp = new int[intervals.length];
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        Arrays.fill(dp, 1);

        int rightMax = intervals[0][1];
        int notSectionCnt = 1;
        for (int i = 1; i < intervals.length; i++) {
            int iLeft = intervals[i][0];
            int iRight = intervals[i][1];
            if(iLeft>=rightMax){
                notSectionCnt++;
                rightMax = Math.max(rightMax,iRight);
            }
            // dp思路
            // for (int j = 0; j < i; j++) {
            //     if (intervals[j][1] <= intervals[i][0]) {
            //         dp[i] = Math.max(dp[i], dp[j] + 1);
            //     }
            // }
        }
        // int maxNonOverlappingLength = Arrays.stream(dp).max().getAsInt();
        return intervals.length - notSectionCnt;
    }
    //endregion

    //region 利用三重循环，遍历出有重叠的，存在则存到map中，直到map中的重叠区域个数为0,实际结果sucks,shit,fuck
    private int doMyEraseOverlapIntervals(int[][] intervals) {
        int resCnt = 0;
        LinkedList<LinkedList<Integer>> intervalList = Arrays.stream(intervals)
                .map(aArr -> Arrays.stream(aArr).boxed().collect(Collectors.toCollection(LinkedList::new)))
                .sorted(new Comparator<LinkedList<Integer>>() {
                    @Override
                    public int compare(LinkedList<Integer> l1,
                                       LinkedList<Integer> l2) {
                        return l1.get(0) - l2.get(0);
                    }
                })
                .collect(Collectors.toCollection(LinkedList::new));
        HashMap<Integer, Boolean> idx2RepeatMap = new HashMap<>();
        for (int i = 0; i < intervalList.size() - 1; i++) {
            LinkedList<Integer> interval1 = intervalList.get(i);
            LinkedList<Integer> interval2 = intervalList.get(i + 1);
            int left1 = interval1.get(0);
            int right1 = interval1.get(1);
            int left2 = interval2.get(0);
            int right2 = interval2.get(1);

            if (left1 == left2 && right1 == right2) {
                idx2RepeatMap.put(i, true);
            }
        }
        if (!idx2RepeatMap.isEmpty()) {
            for (int idx : idx2RepeatMap.keySet()) {
                intervalList.set(idx, null);
                resCnt++;
            }
        }


        while (true) {
            //1.算重叠区间最大的次数,如果 最大次数为0，说明没有重叠的区间
            int maxTimesOverlapping = 0;
            HashMap<Integer, Integer> idxOverlapping2TimesMap = new HashMap<>();
            for (int i = 0; i < intervalList.size() - 1; i++) {
                if (Objects.isNull(intervalList.get(i))) {
                    continue;
                }
                for (int j = i + 1; j < intervalList.size(); j++) {
                    if (Objects.isNull(intervalList.get(j))) {
                        continue;
                    }
                    boolean isOverlapping = isOverlapping(intervalList.get(i),
                            intervalList.get(j));
                    if (isOverlapping) {
                        idxOverlapping2TimesMap.put(i,
                                idxOverlapping2TimesMap.getOrDefault(i, 0) + 1);
                        idxOverlapping2TimesMap.put(j,
                                idxOverlapping2TimesMap.getOrDefault(j, 0) + 1);
                        maxTimesOverlapping = Math.max(Math.max(maxTimesOverlapping,
                                idxOverlapping2TimesMap.get(i)),
                                idxOverlapping2TimesMap.get(j));
                    }
                }
            }
            if (maxTimesOverlapping == 0) {
                break;
            }
            //2.每次移除重叠区间最大次数对应的区间
            for (Integer idx : idxOverlapping2TimesMap.keySet()) {
                if (maxTimesOverlapping == idxOverlapping2TimesMap.get(idx)) {
                    intervalList.remove((int) idx);
                    resCnt++;
                    break;
                }
            }
        }
        return resCnt;
    }


    private boolean isOverlapping(LinkedList<Integer> list1,
                                  LinkedList<Integer> list2) {
        int left1 = list1.get(0);
        int right1 = list1.get(1);

        int left2 = list2.get(0);
        int right2 = list2.get(1);

        if (left1 <= left2 && left2 < right1) {
            return true;
        }

        if (left1 < right2 && right2 <= right1) {
            return true;
        }

        return false;
    }
    //endregion，,

    public static void main(String[] args) {
        // int[][] intervals = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        // int[][] intervals = new int[][]{{1, 2}, {1, 2}, {1, 2}};
        // int[][] intervals = new int[][]{{1, 100}, {11, 22}, {1, 11}, {2, 12}};
        // int[][] intervals = new int[][]{{0, 2}, {1, 3}, {1, 3}, {2, 4}, {3, 5}, {3, 5},
        //         {4, 6}};
        // String arrStr = "[[-39,21],[-38,5],[-67,67],[23,37],[83,95],[-71,-19],[-19,64],[4,31],[37,96],[30,57],[-19,12],[53,75],[-54,83],[-32,38],[-18,16],[-60,9],[92,93],[-12,20],[-37,35],[19,36],[46,56],[45,52],[-67,-29],[30,67],[67,79],[52,98],[59,60],[-63,7],[7,20],[16,59],[83,96],[-59,-3],[33,41],[-67,-49],[-15,67]]";
        // System.out.println(ArrUtils.str2Arr2(arrStr));
        // int[][] intervals = new int[][]{{-39, 21}, {-38, 5}, {-67, 67}, {23, 37}, {83, 95}, {-71, -19}, {-19, 64}, {4, 31}, {37, 96}, {30, 57}, {-19, 12}, {53, 75}, {-54, 83}, {-32, 38}, {-18, 16}, {-60, 9}, {92, 93}, {-12, 20}, {-37, 35}, {19, 36}, {46, 56}, {45, 52}, {-67, -29}, {30, 67}, {67, 79}, {52, 98}, {59, 60}, {-63, 7}, {7, 20}, {16, 59}, {83, 96}, {-59, -3}, {33, 41}, {-67, -49}, {-15, 67}};
        int[][] intervals = new int[][]{{1,2},{2,3},{3,4},{1,3}};
        System.out.println(new NonOverlappingIntervals().eraseOverlapIntervals(intervals));
    }
}
