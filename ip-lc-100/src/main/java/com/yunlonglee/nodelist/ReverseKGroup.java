package com.yunlonglee.nodelist;

/**
 * @author lijie
 * @version 1.0
 * @description 88.k个一组反转链表
 * @date 6/2/22 10:45 下午
 */
public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        int count = 0;
        ListNode cursor = head;
        while (cursor != null) {
            count++;
            cursor = cursor.next;
        }
        cursor = head;
        int[] arr = new int[count];
        int i = 0;
        while (cursor != null) {
            arr[i] = cursor.val;
            i++;
            cursor = cursor.next;
        }

        for (int m = 0, n = k - 1; n < arr.length; m = m + k, n = n + k) {
            int i1 = m;
            int j1 = n;
            while (i1 <= j1) {
                int temp = arr[i1];
                arr[i1] = arr[j1];
                arr[j1] = temp;
                i1++;
                j1--;
            }
        }
        ListNode head1 = new ListNode(arr[0]);
        ListNode tail = head1;
        for(int p=1;p<arr.length;p++){
            ListNode  node = new ListNode(arr[p]);
            tail.next = node;
            tail = tail.next;
        }
        return head1;
    }

    //找出k长度的子链表,反转，并入原链表
}
