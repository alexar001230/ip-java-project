package com.yunlong.lee.dataStructure.linkedList.reverse;

import com.yunlong.lee.utils.list.ListNode;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 两两交换链表中的节点
 * @date 4/4/23 5:12 下午
 * @link https://leetcode.cn/problems/swap-nodes-in-pairs/
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        return doSwapPairs(head);
    }

    private ListNode doSwapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }

    private ListNode myDoSwapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;
        ListNode leftCursor = dummyHead;
        ListNode rightCursor = leftCursor.next;
        ListNode tailCursor = dummyHead;
        boolean headInit = false;
        while (Objects.nonNull(rightCursor)) {
            ListNode tmp = new ListNode(-1);
            tmp.next = rightCursor.next;
            rightCursor.next = leftCursor;
            leftCursor.next = tmp.next;
            if (!headInit) {
                dummyHead.next = rightCursor;
            }
            leftCursor = leftCursor.next;
            rightCursor = leftCursor.next;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.genListNode(1, 2, 3, 4, 5);
        ListNode.print(new SwapNodesInPairs().swapPairs(head));
    }
}
