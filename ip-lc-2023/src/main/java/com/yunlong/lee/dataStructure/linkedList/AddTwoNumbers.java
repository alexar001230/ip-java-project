package com.yunlong.lee.dataStructure.linkedList;

import com.yunlong.lee.utils.list.ListNode;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 8/2/23 3:13 下午
 * @link https://leetcode.cn/problems/add-two-numbers/
 */
public class AddTwoNumbers {
    public static void main(String[] args) {

    }

    //1.解决连续进位问题？
    // 进位问题主要是在一遍循环遍历中，在循环体中前半段生成，后半段就可继承用
    //2.链表标记首尾技巧
    // 一个标记头head，一个标记尾tail，tail = tail.next用来迭代链表节点
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (Objects.isNull(l1)) {
            return l2;
        }
        if (Objects.isNull(l2)) {
            return l1;
        }

        ListNode head = null;
        ListNode tail = null;
        int carry = 0;
        while (Objects.nonNull(l1) || Objects.nonNull(l2)) {
            int l1NodeVal = Objects.isNull(l1) ? 0 : l1.val;
            int l2NodeVal = Objects.isNull(l2) ? 0 : l2.val;

            int sum = l1NodeVal + l2NodeVal;

            if (Objects.isNull(head)) {
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

            if (Objects.nonNull(l1)) {
                l1 = l1.next;
            }

            if (Objects.nonNull(l2)) {
                l2 = l2.next;
            }


            // int nodeValSum = (l1.val + l2.val);
            // int nodeVal = nodeValSum % 10;
            // carry = nodeVal / 10;
            // if (Objects.isNull(head)) {
            //     ListNode aNode = new ListNode();
            //     aNode.val = nodeVal;
            //     head = aNode;
            //     tail = aNode;
            // } else {
            //     ListNode aNode = new ListNode();
            //     aNode.val = (nodeVal + carry) % 10;
            //     carry = (nodeVal + carry) / 10;
            //     tail.next = aNode;
            //     tail = tail.next;
            // }
            // l1 = l1.next;
            // l2 = l2.next;
        }


        // if (Objects.nonNull(l1)) {
        //     tail.next = l1.next;
        // }
        //
        // if (Objects.nonNull(l2)) {
        //     tail.next = l2.next;
        // }

        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

}
