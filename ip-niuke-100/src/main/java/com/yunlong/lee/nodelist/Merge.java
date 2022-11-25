package com.yunlong.lee.nodelist;

import com.yunlonglee.nodelist.ListNode;

import static com.yunlonglee.nodelist.ConstructListNode.constructListNode;

/**
 * @author lijie
 * @version 1.0
 * @description JZ25 合并两个排序的链表
 * @date 2/3/22 3:25 上午
 */
public class Merge {
    public static ListNode merge(ListNode list1, ListNode list2) {
        ListNode head0 = new ListNode(Integer.MIN_VALUE);
        ListNode cursor = head0;
        ListNode cursor1 = list1;
        ListNode cursor2 = list2;
        while (null != cursor1 || null != cursor2) {
            if (null != cursor1 && null != cursor2) {
                if (cursor1.val < cursor2.val) {
                    cursor.next = new ListNode(cursor1.val);
                    cursor1 = cursor1.next;
                } else {
                    cursor.next = new ListNode(cursor2.val);
                    cursor2 = cursor2.next;
                }
                cursor = cursor.next;
            } else {
                if (cursor1 == null) {
                    cursor.next = cursor2;
                    break;
                }
                if (cursor2 == null) {
                    cursor.next = cursor1;
                    break;
                }
            }
        }
        return head0.next;

    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        merge(constructListNode(arr1), constructListNode(arr2));
    }
}
