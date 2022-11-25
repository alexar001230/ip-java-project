package com.yunlonglee.nodelist;

/**
 * @author lijie
 * @version 1.0
 * @description 剑指 Offer 18. 删除链表的节点
 * @date 2/3/22 1:39 上午
 */
public class DeleteNode {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode head0 = new ListNode(Integer.MIN_VALUE);
        head0.next = head;
        ListNode cursor = head;
        ListNode resultHead = head;
        int i = 1;
        while (null != cursor) {
            if (cursor.val == val) {
                if (i == 1) {
                    resultHead = cursor.next;
                } else {
                    resultHead = head;
                }
                head0.next = cursor.next;
                cursor.next = null;
            }
            cursor = cursor.next;
            head0 = head0.next;
            i++;
        }
        return resultHead;
    }
}
