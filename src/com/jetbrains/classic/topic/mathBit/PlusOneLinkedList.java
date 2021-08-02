package com.jetbrains.classic.topic.mathBit;

import com.jetbrains.innerStructure.ListNode;

public class PlusOneLinkedList {
    /*

        why use dummyNode?
        To handle the last use case, one needs so called Sentinel Node. Sentinel nodes are widely used for trees and linked lists as pseudo-heads, pseudo-tails, etc.
        They are purely functional, and usually don't hold any data. Their main purpose is to standardize the situation to avoid edge case handling.


        Algorithm
        Let's identify the rightmost digit which is not equal to nine and increase that digit by one.
        All the following nines should be set to zero.

        1.Initialize sentinel node as ListNode(0) and set it to be the new head: sentinel.next = head.

        2.Find the rightmost digit not equal to nine.

        3.Increase that digit by one.

        4.Set all the following nines to zero.

        5.Return sentinel node if it was set to 1, and head sentinel.next otherwise.
     */
    public ListNode plusOne(ListNode head) {
        // sentinel head
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode notNine = dummyNode;

        // find the rightmost not-nine digit
        while (head != null) {
            if(head.val != 9) {
                notNine = head;
            }
            head = head.next;
        }

        // increase this rightmost not-nine digit by 1
        notNine.val++;
        notNine = notNine.next;

        // set all the following nines to zeros
        while(notNine != null) {
            notNine.val = 0;
            notNine = notNine.next;
        }

        return dummyNode.val != 0 ? dummyNode : dummyNode.next;
    }
}
