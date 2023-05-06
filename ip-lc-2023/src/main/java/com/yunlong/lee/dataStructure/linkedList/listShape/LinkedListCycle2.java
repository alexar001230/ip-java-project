package com.yunlong.lee.dataStructure.linkedList.listShape;

import com.yunlong.lee.utils.list.ListNode;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 142. 环形链表 II
 * @date 5/5/23 1:08 下午
 * @link https://leetcode.cn/problems/linked-list-cycle-ii/
 */
public class LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        return doDetectCycle(head);
    }

    private ListNode doDetectCycle(ListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (Objects.nonNull(fast)) {
            slow = slow.next;
            if (Objects.isNull(slow)) {
                return null;
            }
            ListNode tmp = fast.next;
            if (Objects.isNull(tmp)) {
                return null;
            }
            fast = tmp.next;
            if (Objects.isNull(fast)) {
                return null;
            }
            if (slow.equals(fast)) {
                break;
            }
        }
        fast = head;
        while (!slow.equals(fast)) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
