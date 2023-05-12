package com.yunlong.lee.dataStructure.array.interval;


import com.yunlong.lee.utils.arr.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 919 · 会议室 II
 * @date 4/5/23 5:02 下午
 * @link https://www.lintcode.com/problem/919/description
 */
public class MinMeetingRooms {
    public int minMeetingRooms(List<Interval> intervals) {
        return doMinMeetingRooms(intervals);
    }

    private int doMinMeetingRooms(List<Interval> intervals) {
        Integer[] startArr = intervalIdx2Arr(intervals, true);
        Integer[] endArr = intervalIdx2Arr(intervals, false);
        sort(startArr);
        sort(endArr);
        int startIdx = 0;
        int endIdx = 0;

        int needRooms = 0;
        while (startIdx < intervals.size()) {
            if (startArr[startIdx] >= endArr[endIdx]) {
                needRooms--;
                endIdx++;
            }
            needRooms++;
            startIdx++;
        }
        return needRooms;
    }

    private Integer[] intervalIdx2Arr(List<Interval> intervals, boolean isStart) {
        Integer[] arr = new Integer[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            if (isStart) {
                arr[i] = intervals.get(i).start;
            } else {
                arr[i] = intervals.get(i).end;
            }
        }
        return arr;
    }

    private void sort(Integer[] arr) {
        Arrays.sort(arr, Comparator.comparingInt(a -> a));
    }

    public static void main(String[] args) {
        Interval interval1 = new Interval(0, 30);
        Interval interval2 = new Interval(5, 10);
        Interval interval3 = new Interval(15, 20);
        List<Interval> intervals = Arrays.asList(interval1, interval2,
                interval3);
        System.out.println(new MinMeetingRooms().minMeetingRooms(intervals));
    }
}
