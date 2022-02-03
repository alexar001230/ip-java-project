package com.yunlonglee.nodelist;

import static com.yunlonglee.nodelist.ConstructListNode.constructListNodeDefault;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 26/1/22 5:14 下午
 */
public class ReverseListNode {
    public static ListNode reverseList(ListNode head) {
        //双指针法
        //prev指针先指向前正向前置节点,后移时指向后置节点，后移
        ListNode prev = null;
        //cur指向当前处理节点,后移
        ListNode cur = head;
        //temp指向后置节点，后移
        ListNode temp = null;
        while (null != cur) {
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = constructListNodeDefault();
        reverseList(head);

    }
}
