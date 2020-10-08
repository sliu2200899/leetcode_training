package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class RemoveDuplicatesSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        while (cur != null) {
            int val = cur.val;
            int num = 1;
            while (cur.next != null && cur.next.val == val) {
                cur = cur.next;
                num++;
            }

            if (num != 1) {
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}
