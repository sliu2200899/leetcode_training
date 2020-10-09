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

    // recursive methods
    /*
    1 Start the recursion with head node of the original linked list.

    2 Every recursion call is responsible for swapping a pair of nodes. Let's represent the two nodes to be swapped by firstNode and secondNode.

    3 Next recursion is made by calling the function with head of the next pair of nodes. This call would swap the next two nodes and make further recursive calls
    if there are nodes left in the linked list.

    4 Once we get the pointer to the remaining swapped list from the recursion call, we can swap the firstNode and secondNode i.e. the nodes in the current recursive
     call and then return the pointer to the secondNode since it will be the new head after swapping.
     */
    public ListNode swapPairs2(ListNode head) {
        // base case
        // if the list has no node or has only one node left
        if (head == null || head.next == null) return head;

        // recursive case
        // Nodes to be swapped
        ListNode firstNode = head;
        ListNode secondNode = head.next;

        // Swapping
        firstNode.next = swapPairs(secondNode.next);
        secondNode.next = firstNode;

        // Now head is the second node
        return secondNode;
    }
}