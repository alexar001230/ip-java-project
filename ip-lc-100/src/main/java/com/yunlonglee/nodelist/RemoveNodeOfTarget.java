package com.yunlonglee.nodelist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 14/1/22 1:31 上午
 */


class Solution {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        ListNode n3 = new ListNode(3);
        n2.next = n3;
        ListNode n4 = new ListNode(4);
        n3.next = n4;
//        ListNode n5 = new ListNode(2);
//        n4.next = n5;

        //removeElements(n1, 2);
//        swapPairs(n1);
        removeNthFromEnd(n1, 2);
    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        if (head.next == null && n == 1) {
            return null;
        }
        int interval = n;
        ListNode left = head;
        ListNode right = head;
        int i = 1;
        while (head != null) {
            if (i <= interval) {
                right = right.next;
                i++;
            } else {
                break;
            }
        }

        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = right;
        return head;
    }

    public static ListNode swapPairs(ListNode head) {
        if (null == head) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode post = head.next;
        while (null != post) {
            int temp = pre.val;
            pre.val = post.val;
            post.val = temp;
            if (null != post.next) {
                pre = post.next;
            } else {
                break;
            }
            if (null != pre.next) {
                post = pre.next;
            } else {
                break;
            }
        }
        return head;
    }


    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        List<ListNode> nodes = new ArrayList<>();
        while (head != null) {
            if (head.val != val) {
                nodes.add(new ListNode(head.val));
            }
            head = head.next;
        }
        for (int i = 0; i < nodes.size() - 1; i++) {
            nodes.get(i).next = nodes.get(i + 1);
        }
        return nodes.get(0);
    }
}