package com.yunlong.lee.nodelist;

import com.yunlonglee.nodelist.ListNode;

import static com.yunlonglee.nodelist.ConstructListNode.constructListNode;
import static com.yunlonglee.nodelist.ConstructListNode.getTail;

/**
 * @author lijie
 * @version 1.0
 * @description JZ52 两个链表的第一个公共结点
 * @date 2/3/22 3:46 上午
 */
public class FindFirstCommonNode {
    public static ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode c1 = pHead1,c2 = pHead2;
        while (c1 != c2) {
            c1 = null != c1 ? c1.next : pHead2;
            c2 = null != c2 ? c2.next : pHead1;
        }
        return c1;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,3};
        int[] arr2 = {4,5};
        int[] arr3 = {6,7};

        ListNode list1 = constructListNode(arr1);
        ListNode list2 = constructListNode(arr2);
        ListNode list3 = constructListNode(arr3);
        getTail(list1).next = list3;
        getTail(list2).next = list3;

        findFirstCommonNode(list1,list2);
    }
}
