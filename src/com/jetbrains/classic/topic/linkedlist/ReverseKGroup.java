package com.jetbrains.classic.topic.linkedlist;

import com.jetbrains.innerStructure.ListNode;

public class ReverseKGroup {
    /*
        clarify:
            1, input, output, example
            2. corner cases
            3. constraints
                space: O(1)
                cannot alter the values of hte nodes

        algo:
            get teh number of the nodes in the list

            each time, prev, head and tail of the sublist, next

            reverse teh sublist

            reshuffle rest of the node

            repeat the above procedural until we reach the end of the list

        test:
            1,    2,    3,    4,    5


            i: 5
    */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        // get the length of teh list
        ListNode cur = head;
        int i = 1;
        while (cur != null) {
            cur = cur.next;
            i++;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;
        cur = head;
        int j = 0;
        while (j + k < i) {
            // find some listnodes
            ListNode h = cur;
            int step = 1;
            while (step < k) {
                cur = cur.next;
                step++;
            }

            ListNode tail = cur;
            ListNode next = cur.next;

            // reverse the sublist
            prev.next = reverseList(h, tail.next);

            // reshuffle listnodes
            h.next = next;

            // update the variables
            prev = h;
            cur = next;
            j += k;
        }

        return dummy.next;
    }

    private ListNode reverseList(ListNode head, ListNode tail) {
        ListNode prev = null;
        while (head != tail) {
            ListNode next = head.next;
            head.next = prev;

            prev = head;
            head = next;
        }

        return prev;
    }
}
