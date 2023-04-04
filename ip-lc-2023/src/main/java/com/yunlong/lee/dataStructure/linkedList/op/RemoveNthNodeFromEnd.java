package com.yunlong.lee.dataStructure.linkedList.op;

import com.yunlong.lee.utils.list.ListNode;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 删除倒数第n个链表节点
 * @date 4/4/23 4:25 下午
 * @link https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        return doRemoveNthFromEnd(head, n);
    }

    private ListNode doRemoveNthFromEnd(ListNode head, int n) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return null;
        }
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;
        ListNode rightCursor = dummyHead.next;
        ListNode preRemoveCursor = dummyHead;
        boolean hasNInterval = false;
        while (Objects.nonNull(rightCursor)) {
            if (!hasNInterval) {
                for (int j = 1; j <= n; j++) {
                    rightCursor = rightCursor.next;
                }
                hasNInterval = true;
            } else {
                preRemoveCursor = preRemoveCursor.next;
                rightCursor = rightCursor.next;
            }
        }
        preRemoveCursor.next = preRemoveCursor.next.next;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.genListNode(1, 2);
        int lashNth = 2;
        ListNode.print(new RemoveNthNodeFromEnd().removeNthFromEnd(head, lashNth));
    }
}
