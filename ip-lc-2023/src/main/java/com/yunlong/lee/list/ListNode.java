package com.yunlong.lee.list;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 8/2/23 3:13 下午
 * @link
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
