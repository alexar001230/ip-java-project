package com.yunlonglee.nodelist;

/**
 * @author lijie
 * @version 1.0
 * @description 234. 回文链表
 * @date 24/2/22 5:24 上午
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode cursor = head;
        int len = 0;
        while (null != cursor) {
            len++;
            cursor = cursor.next;
        }
        if (len % 2 != 0) {
            return false;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = head.val;
            head = head.next;
        }

        for (int i = 0, j = len - 1; i <= j; i++, j--) {
            if (arr[i] != arr[j]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
