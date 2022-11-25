package com.yunlonglee.nodelist;

import static com.yunlonglee.nodelist.ConstructListNode.constructListNode;
import static com.yunlonglee.nodelist.ConstructListNode.getTail;

/**
 * @author lijie
 * @version 1.0
 * @description 160. 相交链表
 * @date 16/2/22 12:57 上午
 */
public class GetIntersectionNode {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) {
            return null;
        }
        ListNode cursorA = headA;
        while (cursorA != null) {
            ListNode cursorB = headB;
            while (cursorB != null) {
                if (cursorB == cursorA) {
                    return cursorA;
                }
                cursorB = cursorB.next;
            }
            cursorA = cursorA.next;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arrA = new int[]{4,1};
        int[] arrB = new int[]{5,6,1};
        int[] arrCommon = new int[]{8,4,5};
        ListNode headA = constructListNode(arrA);
        ListNode tailA = getTail(headA);
        ListNode headB = constructListNode(arrB);
        ListNode tailB = getTail(headB);
        ListNode common = constructListNode(arrCommon);
        tailA.next = common;
        tailB.next = common;
        getIntersectionNode(headA,headB);
    }
}
