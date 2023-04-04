package com.yunlong.lee.dataStructure.linkedList.op;

import com.yunlong.lee.utils.list.ListNode;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 203. 移除链表元素
 * @date 4/4/23 7:15 下午
 * @link https://leetcode.cn/problems/remove-linked-list-elements/
 */
public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        return doRemoveElements(head,val);
    }

    private ListNode doRemoveElements(ListNode head,int val){
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;
        ListNode cursor = dummyHead.next;
        ListNode preCursor = dummyHead;
        while(Objects.nonNull(cursor)){
            if(cursor.val == val){
                preCursor.next = cursor.next;
                cursor = cursor.next;
            }else{
                preCursor = cursor;
                cursor = cursor.next;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.genListNode(7,7,6,7);
        ListNode.print(new RemoveLinkedListElements().removeElements(head,7));
    }
}
