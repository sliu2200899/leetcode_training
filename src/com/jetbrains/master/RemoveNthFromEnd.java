package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class RemoveNthFromEnd {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // time: O(n)
        // space: O(1)

        ListNode pr1 = head;
        int i = 0;
        while (i < n) {
            pr1= pr1.next;
            i++;
        }

        ListNode pr2 = head, prev = head;
        while (pr1 != null) {
            prev = pr2;
            pr1 = pr1.next;
            pr2 = pr2.next;
        }

        if (pr2 == head) {
            return head.next;
        } else {
            prev.next = pr2.next;
            return head;
        }
    }
}
