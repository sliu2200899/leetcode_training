package com.jetbrains.classic.topic.linkedlist;

import com.jetbrains.innerStructure.ListNode;

public class InsertionSortList {
    /*
        algo:
            we need a dummy node, and we need to find a right position to place the node 4 into the sorted part.
            dummy -> null | 4 -> 2 -> 1 -> 3 -> null
              |             |     |
            prev           cur   next
            because we are dealing with linked list, it is not an array, we cannot access a position using index.
            therefore, to find a place to insert a node, we need to use a pointer prev to traverse the sorted part from the beginning.

            so we can insert the node 4 right behind the dummy node,
            But before inserting the node 4, we need to unlink it with unsorted part.
            so we may use a pointer next to hold the rest of the unsorted part.
            and then, we can finish the insertion, which is like this.
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) return null;

        ListNode dummyNode = new ListNode(-1);
        ListNode cur = head, dcur = dummyNode;

        while (cur != null) {

            ListNode next = cur.next;

            dcur = dummyNode;
            // find the proper position to insert
            while (dcur.next != null && dcur.next.val < cur.val) {
                dcur = dcur.next;
            }

            ListNode dnext = dcur.next;
            dcur.next = cur;
            cur.next = dnext;

            cur = next;

        }

        return dummyNode.next;
    }
}
