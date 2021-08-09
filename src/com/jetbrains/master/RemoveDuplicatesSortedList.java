package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class RemoveDuplicatesSortedList {
    /*
        82 remove duplicated sorted list
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode cur = head, prev = dummyNode;

        while (cur != null) {
            int count = 1;

            while(cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                count++;
            }

            if (count > 1) {

                if (cur.next == null) {
                    prev.next = null;
                } else {
                    prev.next = cur.next;
                    cur = cur.next;
                }
            } else {
                prev = cur;
                cur = cur.next;
            }
        }

        return dummyNode.next;
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
