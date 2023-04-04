package com.yunlong.lee.dataStructure.linkedList;

import com.yunlong.lee.utils.list.ListNode;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 合并k个升序链表
 * @date 3/4/23 5:07 下午
 * @link https://leetcode.cn/problems/merge-k-sorted-lists/
 */
public class MergeKSortedLinkedList {
    public ListNode mergeKLists(ListNode[] lists) {
        if(Objects.isNull(lists) || lists.length == 0){
            return null;
        }
        return mergeKRecursive(lists, 0, lists.length - 1);
    }

    private ListNode mergeKRecursive(ListNode[] lists, int startIdx, int endIdx) {
        if (endIdx - startIdx == 1) {
            return merge2SortedList(lists[startIdx], lists[endIdx]);
        }
        if (endIdx - startIdx == 0) {
            return lists[startIdx];
        }
        int mid = (startIdx + endIdx) / 2;
        ListNode left = mergeKRecursive(lists, startIdx, mid);
        ListNode right = mergeKRecursive(lists, mid + 1, endIdx);
        return merge2SortedList(left, right);
    }

    private ListNode merge2SortedList(ListNode list1, ListNode list2) {
        if (Objects.isNull(list1)) {
            return list2;
        }
        if (Objects.isNull(list2)) {
            return list1;
        }
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode cursor = dummyHead;
        while (Objects.nonNull(list1) && Objects.nonNull(list2)) {
            if (list1.val < list2.val) {
                ListNode newNode = new ListNode(list1.val);
                list1 = list1.next;
                cursor.next = newNode;
            } else {
                ListNode newNode = new ListNode(list2.val);
                list2 = list2.next;
                cursor.next = newNode;
            }
            cursor = cursor.next;
        }
        if (Objects.nonNull(list1)) {
            cursor.next = list1;
        }
        if (Objects.nonNull(list2)) {
            cursor.next = list2;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode list1 = ListNode.genListNode(1,4,5);
        ListNode list2 = ListNode.genListNode(1,3,4);
        ListNode list3 = ListNode.genListNode(2,6);
        ListNode[] listArr = new ListNode[]{list1,list2,list3};
        ListNode.print(new MergeKSortedLinkedList().mergeKLists(listArr));
    }
}
