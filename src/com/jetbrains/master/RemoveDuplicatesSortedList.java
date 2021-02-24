package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class RemoveDuplicatesSortedList {
    /*
        82 remove duplicated sorted list
     */
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

    /*
        preferred way to solve the problem
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy, cur = head;
        while (cur != null && cur.next != null) {
            /*
                prev.next:  head
                if cur.val == cur.next.val
                    while loop to find all of the duplicates
                    set prev.next = cur;
                else
                    update prev and cur
            */
            if (cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }

                prev.next = cur.next;
                cur = cur.next;
            }
            else {
                prev = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
