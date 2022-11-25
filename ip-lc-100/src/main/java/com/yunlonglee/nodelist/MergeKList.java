package com.yunlonglee.nodelist;

import static com.yunlonglee.nodelist.MergeTwoList.mergeTwoLists;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 6/2/22 10:32 下午
 */
public class MergeKList {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;
        int i = 0;
        while (i < lists.length) {
            res = mergeTwoLists(res, lists[i]);
            i++;
        }
        return res;
    }
}
