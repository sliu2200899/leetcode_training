package com.jetbrains.hard;

import com.jetbrains.innerStructure.ListNode;

public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {

        ListNode dummy = new ListNode(-1);
        ListNode curr = head, prevNode, nextNode;

        while (curr != null) {
            // At each iteration, we insert an element into the resulting list.
            prevNode = dummy;
            nextNode = dummy.next;

            // find the position to insert the current node
            while (nextNode != null) {
                if (curr.val < nextNode.val)
                    break;
                prevNode = nextNode;
                nextNode = nextNode.next;
            }
            ListNode nextIter = curr.next;
            // insert the current node to the new list
            curr.next = nextNode;
            prevNode.next = curr;

            // moving on to the next iteration
            curr = nextIter;
        }

        return dummy.next;
    }
}
