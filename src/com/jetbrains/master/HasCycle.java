package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class HasCycle {
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        // define fast slow pointer..
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }
}
