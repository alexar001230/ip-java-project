package com.yunlong.lee.dataStructure.linkedList.sort;

import com.yunlong.lee.utils.list.ListNode;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 合并两个有序链表
 * @date 3/4/23 7:01 下午
 * @link https://leetcode.cn/problems/merge-two-sorted-lists/
 */
public class Merge2SortedList {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode mainCursor = dummyHead;
        ListNode list1Cursor = list1;
        ListNode list2Cursor = list2;
        while (Objects.nonNull(list1Cursor) && Objects.nonNull(list2Cursor)) {
            if (list1Cursor.val < list2Cursor.val) {
                mainCursor.next = list1Cursor;
                list1Cursor = list1Cursor.next;
            } else {
                mainCursor.next = list2Cursor;
                list2Cursor = list2Cursor.next;
            }
            mainCursor = mainCursor.next;
        }
        if (Objects.nonNull(list1Cursor)) {
            mainCursor.next = list1Cursor;
        }
        if (Objects.nonNull(list2Cursor)) {
            mainCursor.next = list2Cursor;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode list1 = ListNode.genListNode(1, 3, 5, 7, 9, 11);
        ListNode list2 = ListNode.genListNode(2, 4, 6, 8);
        ListNode.print(new Merge2SortedList().mergeTwoLists(list1, list2));
    }
}
