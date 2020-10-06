package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class ReverseList {
    // iterative solution
    public static ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }

        return newHead;
    }

    // recursive solution
    public ListNode reverseList2(ListNode head) {
        return reverseListInt(head, null);
    }

    private ListNode reverseListInt(ListNode node, ListNode tail) {
        // base case
        if (node == null) return tail;

        // recursive case
        ListNode next = node.next;
        node.next = tail;

        return reverseListInt(next, node);
    }
}
