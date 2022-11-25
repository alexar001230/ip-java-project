package com.yunlonglee.nodelist;

import java.util.ArrayList;
import java.util.List;

import static com.yunlonglee.nodelist.ConstructListNode.constructListNode;
import static com.yunlonglee.nodelist.ConstructListNode.listNodePrint;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 14/1/22 1:31 上午
 */


class RemoveNthFromEnd {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        if (head.next == null && n == 1) {
            return null;
        }
        int nodesSize = 0;
        ListNode count = head;
        while (null != count) {
            nodesSize++;
            count = count.next;
        }
        if (n == nodesSize) {
            return removeHead(head);
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
        if (interval == 1) {
            left.next = right.next;
        } else {
            left.next = right;
        }

        return head;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2};
        ListNode listNode = constructListNode(arr);
        removeNthFromEnd(listNode, 2);
        listNodePrint(listNode);
    }

    private static ListNode removeHead(ListNode head) {
        head = head.next;
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