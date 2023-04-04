package com.yunlong.lee.dataStructure.linkedList.sort;

import com.yunlong.lee.utils.list.ListNode;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 链表排序
 * @date 16/3/23 6:51 下午
 * @link https://leetcode.cn/problems/sort-list/
 */
public class SortList {

    //region 归并排序
    public ListNode sortListByMerging(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }
        return mergeSortList(head, null);
    }

    private ListNode mergeSortList(ListNode head, ListNode tail) {
        if (null == head) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        //1.找中点
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        //2.分割递归归并排序
        ListNode sortedList1 = mergeSortList(head, mid);
        ListNode sortedList2 = mergeSortList(mid, tail);
        //3.合并
        return mergeSortedList(sortedList1, sortedList2);
    }

    private ListNode mergeSortedList(ListNode sortedList1Head,
                                     ListNode sortedList2Head) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode mainCursor = dummyHead;
        ListNode list1Cursor = sortedList1Head;
        ListNode list2Cursor = sortedList2Head;

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
        } else if (Objects.nonNull(list2Cursor)) {
            mainCursor.next = list2Cursor;
        }
        return dummyHead.next;
    }


    //endregion

    //region 快慢指针  n平方算法,oj会判定超时
    //单纯使用双指针,实际上还是在进行冒泡排序,所以会超时，
    public ListNode sortedListByTwoPointer(ListNode head) {
        //使用快慢双指针
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast != null) {
            if (slow.val > fast.val) {
                int tmp = slow.val;
                slow.val = fast.val;
                fast.val = tmp;
            } else {
                fast = fast.next;
            }
            if (fast == null) {
                slow = slow.next;
                fast = slow.next;
            }
        }
        return head;
    }
    //endregion

    //region byArr
    //转换成数组做法,内存使用为n，时间复杂度为logN
    public ListNode sortListByArr(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        int[] valArr = new int[len];
        cur = head;
        int i = 0;
        while (cur != null) {
            valArr[i] = cur.val;
            cur = cur.next;
            i++;
        }
        Arrays.sort(valArr);
        ListNode newHead = new ListNode(valArr[0]);
        ListNode curNode = newHead;
        for (int j = 1; j < valArr.length; j++) {
            ListNode newNode = new ListNode(valArr[j]);
            curNode.next = newNode;
            curNode = curNode.next;
        }
        return newHead;
    }
    //endregion

    public static void main(String[] args) {
        ListNode head = ListNode.genListNode(5, 4, 2, 1, 3);
        ListNode.print(new SortList().sortListByMerging(head));
    }
}
