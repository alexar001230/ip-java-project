package com.yunlonglee.nodelist;

import static com.yunlonglee.nodelist.ConstructListNode.constructListNode;
import static com.yunlonglee.nodelist.ConstructListNode.getTail;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 26/1/22 3:35 下午
 */
public class HasCycle {
    public static boolean hasCycle(ListNode head) {
        if(null==head || null == head.next){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(null!=fast){
            slow = slow.next;
            fast = fast.next.next;
            if(slow.equals(fast)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,2,0,4};
        ListNode head = constructListNode(arr);
        ListNode tail = getTail(head);
        tail.next = head.next;
        System.out.println(hasCycle(head));
    }
}
