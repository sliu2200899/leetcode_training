package com.jetbrains.classic.topic.linkedlist;

import com.jetbrains.innerStructure.ListNode;

public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode fast = head, slow = head, pre = null;

        // step 1. cut the list to two halves
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        pre.next = null;

        ListNode mid = slow;

        // sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(mid);

        // merge l1 and l2
        return mergeList(l1, l2);
    }

    private ListNode mergeList(ListNode t1, ListNode t2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (t1 != null && t2 != null) {
            if (t1.val < t2.val) {
                cur.next = t1;
                t1 = t1.next;
            } else {
                cur.next = t2;
                t2 = t2.next;
            }

            cur = cur.next;
        }

        if (t1 == null) {
            cur.next = t2;
        }

        if (t2 == null) {
            cur.next = t1;
        }

        return dummy.next;
    }
}
