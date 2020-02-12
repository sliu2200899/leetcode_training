package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class MergeTwoLists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode tr = dummyNode;
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                tr.next = l2;
                l2 = l2.next;
            } else {
                tr.next = l1;
                l1 = l1.next;
            }
            tr = tr.next;
        }

        if (l1 != null) {
            tr.next = l1;
        }
        if (l2 != null) {
            tr.next = l2;
        }

        return dummyNode.next;
    }
}
