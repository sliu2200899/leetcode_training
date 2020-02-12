package com.jetbrains;

import com.jetbrains.master.*;
import com.jetbrains.innerStructure.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) {
        // two sum
//        TwoSum obj = new TwoSum();
//
//        int[] nums = {1, 2, 6, 10, 15};
//        int target = 8;
//
//        int[] arr = obj.twoSum(nums, target);
//        for (int ar : arr) {
//            System.out.println(ar);
//        }

        // add two numbers
//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
//
//        ListNode out = AddTwoNumbers.addTwoNumbersRecursive(l1, l2);
//        ListNode tr = out;
//        while (tr != null) {
//            System.out.println(tr.val);
//            tr = tr.next;
//        }

        // add two numbers 2
//        List<ListNode> res = new LinkedList<>();
//        ListNode newNode = new ListNode(10);
//        res.add(0, newNode);
//
//        ListNode newNode2 = new ListNode(11);
//        res.add(0, newNode2);
//
//        ListIterator<ListNode> listIterator = res.listIterator();
//        while(listIterator.hasNext()) {
//            System.out.println(listIterator.next().val);
//        }
//
//        String[] test = {"uuu", "asdf", "asdf", "asdf", "asd"};
//        StringBuilder sb = new StringBuilder();
//        for (String s : test) {
//            sb.append(s).append(" ");
//        }
//        System.out.printf(sb.toString());
//        System.out.println(Arrays.toString(test));

        // remove Nth node from end of the list
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);

        ListNode out = RemoveNthFromEnd.removeNthFromEnd(l1, 2);
        while (out != null) {
            System.out.print(out.val + " ");
            out = out.next;
        }
    }
}
