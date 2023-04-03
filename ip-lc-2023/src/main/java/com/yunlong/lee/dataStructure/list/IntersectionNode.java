package com.yunlong.lee.dataStructure.list;

import com.yunlong.lee.utils.list.ListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 9/3/23 1:05 下午
 * @link https://leetcode.cn/problems/intersection-of-two-linked-lists/
 */

/**
 * 猫眼一面，写题
 * 当时理解的链表是交叉的节点
 * 其实是交叉后，两个单链表会会和成一个链表
 *
 * 有这样的特性，则可以通过减掉公共部分，然后调换遍历指针最终找到公共节点
 *
 * 更简单的方法是，利用hashMap来解
 */
public class IntersectionNode {
    public ListNode getIntersectionNodeWithMap(ListNode headA, ListNode headB) {
        if (Objects.isNull(headA) || Objects.isNull(headB)) {
            return null;
        }
        Map<Integer, ListNode> nodeAMap = new HashMap<Integer, ListNode>();
        ListNode curNodeA = headA;

        while (Objects.nonNull(curNodeA)) {
            nodeAMap.put(curNodeA.hashCode(), curNodeA);
            curNodeA = curNodeA.next;
        }

        ListNode curNodeB = headB;
        while (Objects.nonNull(curNodeB)) {
            ListNode sameNode = nodeAMap.get(curNodeB.hashCode());
            if (Objects.nonNull(sameNode)) {
                return sameNode;
            }
            curNodeB = curNodeB.next;
        }
        return null;
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (Objects.isNull(headA) || Objects.isNull(headB)) {
            return null;
        }
        ListNode curNodeA = headA;
        ListNode curNodeB = headB;
        //不相等继续找
        while (!Objects.equals(curNodeA, curNodeB)) {
            //节点为空,遍历节点指针调换指向另一个链表的头,这步相当于求两个链表除公共部分的差值
            curNodeA = Objects.isNull(curNodeA) ? headB : curNodeA.next;
            curNodeB = Objects.isNull(curNodeB) ? headA : curNodeB.next;
        }
        return curNodeA;
    }

    public static void main(String[] args) {
        ListNode node11 = new ListNode(11);
        ListNode node12 = new ListNode(12);
        ListNode node13 = new ListNode(13);
        ListNode node14 = new ListNode(14);

        node11.next = node12;
        node12.next = node13;
        node13.next = node14;

        ListNode node21 = new ListNode(21);
        ListNode node22 = new ListNode(22);
        ListNode node23 = new ListNode(23);
        ListNode node24 = new ListNode(24);

        node21.next = node22;
        node22.next = node23;
        node23.next = node12;

        System.out.println(new IntersectionNode().getIntersectionNodeWithMap(
                node11, node21).val);

    }
}
