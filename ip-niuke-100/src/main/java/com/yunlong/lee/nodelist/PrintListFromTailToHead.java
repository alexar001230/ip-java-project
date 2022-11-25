package com.yunlong.lee.nodelist;

import com.yunlonglee.nodelist.ListNode;

import java.util.ArrayList;
import java.util.List;

import static com.yunlonglee.nodelist.ConstructListNode.constructListNode;

/**
 * @author lijie
 * @version 1.0
 * @description JZ6 从尾到头打印链表
 * @date 2/3/22 2:45 上午
 */
public class PrintListFromTailToHead {
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ListNode prev = null;
        ListNode cursor = listNode;
        while(null!=cursor){
            //先保存遍历节点的下个节点
            ListNode next = cursor.next;
            //当前节点从原链表摘除
            cursor.next = prev;
            //前置节点指向当前节点(已经摘除)
            prev = cursor;
            //游标节点后移(之前保存了)
            cursor = next;
        }
        ArrayList<Integer> list = new ArrayList<>();
        cursor = prev;
        while (null != cursor) {
            list.add(cursor.val);
            System.out.println(cursor.val);
            cursor = cursor.next;
        }

        return list;
    }

    public static void main(String[] args) {
        ListNode listNode = constructListNode(new int[]{1,2,3,4});
        printListFromTailToHead(listNode);
    }
}
