package com.jetbrains.classic;

import com.jetbrains.innerStructure.ListNode;

public class DeleteDuplicates2 {
    public static ListNode deleteDuplicates(ListNode head) {
        // dummy
        // two pointers, pre cur
        // pre, cur.val == cur.next.val
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy, cur = head;

        while (cur != null && cur.next != null) {
            if (cur.val != cur.next.val) {
                // define unique list node
                pre = cur;
                cur = cur.next;
            } else {
                // found the duplicates
                // cur.val == cur.next.val
                while (cur!= null && cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }

                pre.next = cur.next;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
