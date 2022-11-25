package com.yunlonglee.nodelist;

import java.util.Arrays;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 27/1/22 4:54 上午
 */
public class SortListNode {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        //1.链表转数组
        //2.数组排序
        //3.数组拉链
        ListNode cursor = head;
        int length = 0;
        while (null != cursor) {
            length = length + 1;
            cursor = cursor.next;
        }
        int[] arr = new int[length];
        int i = 0;
        while (head != null) {
            arr[i] = head.val;
            i++;
            head = head.next;
        }
        Arrays.sort(arr);
        ListNode result = new ListNode(arr[0]);
        ListNode cur = result;
        for (int j = 1; j < length; j++) {
            ListNode newNode = new ListNode(arr[j]);
            cur.next = newNode;
            cur = cur.next;
        }
        return result;
    }
}
