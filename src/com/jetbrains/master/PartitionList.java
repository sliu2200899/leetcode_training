package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class PartitionList {
    public static ListNode partition(ListNode head, int x) {
        if (head == null) return null;

        // define smallHead, largeHead;
        // before and after are the two pointers used to create the two list
        // before_head and after_head are used to save the heads of the two lists.
        // All of these are initialized with the dummy nodes created.
        ListNode smallDummy = new ListNode(-1), smallCur = smallDummy;
        ListNode largeDummy = new ListNode(-1), largeCur = largeDummy;

        ListNode cur = head;

        while (cur != null) {
            if (cur.val < x) {
                // If the original list node is lesser than the given x,
                // assign it to the before list.
                smallCur.next = cur;
                smallCur = smallCur.next;
            } else {
                // If the original list node is greater or equal to the given x,
                // assign it to the after list.
                largeCur.next = cur;
                largeCur = largeCur.next;
            }
            cur = cur.next;
        }

        smallCur.next = largeDummy.next;
        largeCur.next = null;

        return smallDummy.next;
    }
}
