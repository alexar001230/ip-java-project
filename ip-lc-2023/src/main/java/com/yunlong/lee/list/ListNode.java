package com.yunlong.lee.list;

import java.util.Objects;

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

    public static ListNode genListNode(int... vals) {
        ListNode head = new ListNode(vals[0]);
        ListNode curNode = head;
        for (int i = 1; i < vals.length; i++) {
            ListNode newNode = new ListNode(vals[i]);
            curNode.next = newNode;
            curNode = curNode.next;
        }
        return head;
    }

    public static void print(ListNode head){
        while(Objects.nonNull(head)){
            System.out.print(head.val);
            if(Objects.nonNull(head.next)){
                System.out.print("-->");
            }
            head = head.next;
        }
    }
}
