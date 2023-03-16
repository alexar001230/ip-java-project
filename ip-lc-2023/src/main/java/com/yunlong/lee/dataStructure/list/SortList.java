package com.yunlong.lee.dataStructure.list;

import java.util.Arrays;

/**
 * @author lijie
 * @version 1.0
 * @description 链表排序
 * @date 16/3/23 6:51 下午
 * @link https://leetcode.cn/problems/sort-list/
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        if(null == head || head.next == null){
            return head;
        }
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        int[] valArr = new int[len];
        cur = head;
        int i = 0;
        while (cur != null) {
            valArr[i] = cur.val;
            cur = cur.next;
            i++;
        }
        Arrays.sort(valArr);
        ListNode newHead = new ListNode(valArr[0]);
        ListNode curNode = newHead;
        for (int j = 1; j < valArr.length; j++) {
            ListNode newNode = new ListNode(valArr[j]);
            curNode.next = newNode;
            curNode = curNode.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.genListNode(5, 4, 2, 1, 3);
        ListNode.print(new SortList().sortList(head));
    }
}
