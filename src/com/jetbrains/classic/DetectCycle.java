package com.jetbrains.classic;

import com.jetbrains.innerStructure.ListNode;

public class DetectCycle {
    public static ListNode detectCycle(ListNode head) {
        if (head == null) return null;

        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
