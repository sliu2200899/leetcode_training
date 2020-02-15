package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class SwapPairs {
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        while (head != null && head.next != null) {
            // Nodes to be swapped
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            // Swapping
            pre.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            // update the head and prevNode for next swap
            pre = firstNode;
            head = firstNode.next; // jump
        }

        return dummy.next;
    }
}
