package com.yunlonglee.nodelist;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static com.yunlonglee.nodelist.ConstructListNode.constructListNode;
import static com.yunlonglee.nodelist.ConstructListNode.listNodePrint;

/**
 * @author lijie
 * @version 1.0
 * @description 2.两数相加
 * @date 9/2/22 2:11 下午
 */
public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //声明头节点，在循环中初始化
        ListNode head = null;
        //生成链表的遍历指针
        ListNode tail = null;
        //表示进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            int l1Val = null == l1 ? 0 : l1.val;
            int l2Val = null == l2 ? 0 : l2.val;
            int sum = l1Val + l2Val;
            if (null == head) {
                head = new ListNode(sum % 10);
                carry = sum / 10;
                tail = head;
            } else {
                ListNode toAdd = new ListNode();
                toAdd.val = (sum + carry) % 10;
                carry = (sum + carry) / 10;
                tail.next = toAdd;
                tail = tail.next;
            }
            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if(carry > 0){
            tail.next = new ListNode(carry);
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = constructListNode(new int[]{2,4,3});
        ListNode l2 = constructListNode(new int[]{5,6,4});
        listNodePrint(addTwoNumbers(l1,l2));
    }
}
