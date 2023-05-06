package com.yunlong.lee.dataStructure.array.interval;

import com.yunlong.lee.utils.arr.Interval;

import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 920 · 会议室
 * @date 4/5/23 5:02 下午
 * @link https://www.lintcode.com/problem/920
 */
public class CanAttendMeetings {
    public boolean canAttendMeetings(List<Interval> intervals) {
        return doCanAttendMeetings(intervals);
    }

    private boolean doCanAttendMeetings(List<Interval> intervals) {
        for (int i = 0; i < intervals.size() - 1; i++) {
            for (int j = i + 1; j < intervals.size(); j++) {
                if (isOverlapped(intervals.get(i), intervals.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isOverlapped(Interval interval1, Interval interval2) {
        Interval big = interval1;
        Interval small = interval2;

        int size1 = interval1.end - interval1.start;
        int size2 = interval2.end - interval2.start;
        if (size1 < size2) {
            big = interval2;
            small = interval1;
        }


        int left1 = small.start;
        int right1 = small.end;
        if (left1 > big.start && left1 < big.end) {
            return true;
        }
        if (right1 > big.start && right1 < big.end) {
            return true;
        }

        if (left1 >= big.start && right1 <= big.end) {
            return true;
        }

        return false;
    }
}
