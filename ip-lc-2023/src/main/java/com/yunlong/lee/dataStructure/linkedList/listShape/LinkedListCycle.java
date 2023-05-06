package com.yunlong.lee.dataStructure.linkedList.listShape;

import com.yunlong.lee.utils.list.ListNode;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 141. 环形链表
 * @date 5/5/23 12:53 下午
 * @link https://leetcode.cn/problems/linked-list-cycle/
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        return doHasCycle(head);
    }

    private boolean doHasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (Objects.nonNull(fast)) {
            slow = slow.next;
            if (Objects.isNull(slow)) {
                return false;
            }
            ListNode tmp = fast.next;
            if (Objects.isNull(tmp)) {
                return false;
            }
            fast = fast.next.next;
            if (slow.equals(fast)) {
                return true;
            }
        }
        return false;
    }
}
