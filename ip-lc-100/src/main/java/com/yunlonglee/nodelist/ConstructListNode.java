package com.yunlonglee.nodelist;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 26/1/22 3:36 下午
 */
public class ConstructListNode {

    public static ListNode constructListNodeDefault(){
        int[] arr = new int[]{3,2,0,4};
        return constructListNode(arr);
    }

    public static ListNode constructListNode(int[] arr) {
        ListNode head = new ListNode(arr[0]);
        ListNode cursor = head;
        for (int i = 1; i < arr.length; i++) {
            ListNode node = new ListNode(arr[i]);
            cursor.next = node;
            cursor = cursor.next;
        }
        return head;
    }

    public static ListNode getTail(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }
}
