package com.yunlong.lee.nodelist;

import com.yunlonglee.nodelist.ListNode;

/**
 * @author lijie
 * @version 1.0
 * @description JZ24 反转链表
 * @date 2/3/22 3:22 上午
 */
public class ReverseList {
    public ListNode ReverseList(ListNode head) {
        ListNode prev = null;
        ListNode cursor = head;
        while (null != cursor) {
            ListNode next = cursor.next;
            cursor.next = prev;
            prev = cursor;
            cursor = next;
        }
        return prev;
    }
}
