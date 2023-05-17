package com.yunlong.lee.dataStructure.linkedList.op.reverse;

import com.yunlong.lee.utils.list.ListNode;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 92. 反转链表 II
 * @date 13/5/23 3:57 下午
 * @link https://leetcode.cn/problems/reverse-linked-list-ii/
 */
public class ReverseBetween {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        return doReverseBetween(head, left, right);
    }

    private ListNode doReverseBetween(ListNode head, int left, int right) {

        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return head;
        }

        //跟踪原链表
        ListNode dummyHead = new ListNode(Integer.MAX_VALUE);
        dummyHead.next = head;
        ListNode preLeft;
        ListNode cursor = dummyHead;
        //跟踪翻转链表
        ListNode dummyBetweenHead = new ListNode(Integer.MIN_VALUE);
        int cnt = right - left + 1;
        if (cnt == 1) {
            return head;
        }
        while (Objects.nonNull(cursor)) {
            preLeft = cursor;
            cursor = cursor.next;
            left--;
            if (left == 0) {
                ListNode newCursor = cursor;
                ListNode tail = newCursor;
                while (cnt > 0) {
                    //保存原信息
                    ListNode toReverse = newCursor;
                    newCursor = toReverse.next;
                    //操作翻转
                    toReverse.next = dummyBetweenHead.next;
                    dummyBetweenHead.next = toReverse;
                    cnt--;
                }
                preLeft.next = dummyBetweenHead.next;
                tail.next = newCursor;
                break;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.genListNode(1, 2);
        ListNode.print(new ReverseBetween().reverseBetween(head, 1, 2));
    }


}
