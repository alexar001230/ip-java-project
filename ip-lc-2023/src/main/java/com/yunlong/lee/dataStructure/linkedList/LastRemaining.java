package com.yunlong.lee.dataStructure.linkedList;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 剑指 Offer 62. 圆圈中最后剩下的数字
 * @date 8/5/23 8:51 下午
 * @link https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 */
public class LastRemaining {
    public int lastRemaining(int n, int m) {
        return doLastRemaining(n, m);
    }

    class DLinkNode {
        public int val;
        public DLinkNode post;

        public DLinkNode(int val) {
            this.val = val;
        }
    }

    private int doLastRemaining(int n, int m) {
        if (m == 1) {
            return n - 1;
        }
        DLinkNode dummyHead = new DLinkNode(Integer.MIN_VALUE);
        DLinkNode tail = dummyHead;
        for (int i = 0; i < n; i++) {
            DLinkNode aNew = new DLinkNode(i);
            tail.post = aNew;
            tail = tail.post;
        }
        tail.post = dummyHead.post;
        DLinkNode cursor = dummyHead.post;
        DLinkNode pre = dummyHead;
        int i = 1;
        while (Objects.nonNull(cursor) && cursor.post != cursor) {
            while (i <= m) {
                if (i == m) {
                    pre.post = cursor.post;
                    cursor = pre.post;
                    break;
                } else {
                    pre = cursor;
                    cursor = cursor.post;
                    i++;
                }
            }
            i = 1;
        }
        return cursor.val;
    }


    public static void main(String[] args) {
        int n = 5;
        int m = 7;
        System.out.println(new LastRemaining().lastRemaining(n, m));
    }
}
