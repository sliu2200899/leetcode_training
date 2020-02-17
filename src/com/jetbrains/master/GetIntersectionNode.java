package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class GetIntersectionNode {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // we don't need to know the length of two lists and diff.. so just use two iterations
        ListNode curA = headA, curB = headB;
        while (curA != curB) {
            // two senoria worth noting ..
            // 1. there exists intersection between two lists
            // 2. no intersection
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }

        return curA;
    }
}
