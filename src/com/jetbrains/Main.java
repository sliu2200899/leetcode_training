package com.jetbrains;

import com.jetbrains.classic.DeleteDuplicates2;
import com.jetbrains.classic.sorting.*;
import com.jetbrains.hard.ReorderList;
import com.jetbrains.innerStructure.*;
import com.jetbrains.classic.MergeKLists;

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
//        ListNode l1 = new ListNode(1);
//        l1.next = new ListNode(2);
//        l1.next.next = new ListNode(3);
//        l1.next.next.next = new ListNode(4);
//        l1.next.next.next.next = new ListNode(5);
//
//        ListNode out = RemoveNthFromEnd.removeNthFromEnd(l1, 2);
//        while (out != null) {
//            System.out.print(out.val + " ");
//            out = out.next;
//        }

        // swap nodes
//        ListNode l1 = new ListNode(1);
//        l1.next = new ListNode(2);
//        l1.next.next = new ListNode(3);
//        l1.next.next.next = new ListNode(4);
//        l1.next.next.next.next = new ListNode(5);
//
//        ListNode out = SwapPairs.swapPairs(l1);
//        while (out != null) {
//            System.out.print(out.val + " ");
//            out = out.next;
//        }

        // delete duplicates
//        ListNode l1 = new ListNode(1);
//        l1.next = new ListNode(2);
//        l1.next.next = new ListNode(3);
//        l1.next.next.next = new ListNode(3);
//        l1.next.next.next.next = new ListNode(5);
//
//        ListNode out = DeleteDuplicates2.deleteDuplicates(l1);
//        while (out != null) {
//            System.out.print(out.val + " ");
//            out = out.next;
//        }

        // merge K sorted list
//        ListNode l1 = new ListNode(1);
//        l1.next = new ListNode(2);
//        l1.next.next = new ListNode(3);
//
//        ListNode l2 = new ListNode(1);
//        l2.next = new ListNode(3);
//        l2.next.next = new ListNode(7);
//
//        ListNode l3 = new ListNode(2);
//        l3.next = new ListNode(2);
//        l3.next.next = new ListNode(9);
//
//        ListNode[] lists = {l1, l2, l3};
//
//        ListNode out = MergeKLists.mergeKList2(lists);
//        while (out != null) {
//            System.out.print(out.val + " ");
//            out = out.next;
//        }

        // delete duplicates
//        ListNode l1 = new ListNode(1);
//        l1.next = new ListNode(2);
//        l1.next.next = new ListNode(3);
//        l1.next.next.next = new ListNode(4);
//        l1.next.next.next.next = new ListNode(5);
//
//        ReorderList.reorderList(l1);
//        while (l1 != null) {
//            System.out.print(l1.val + " ");
//            l1 = l1.next;
//        }

        // bubble sort
//        int[] arr = {2, 1, 4, 6, 2, 9};
//        BubbleSort.sort(arr);
//        for (int i : arr) {
//            System.out.println(i);
//        }

        // selection sort
//        int[] arr = {2, 1, 4, 6, 2, 9};
//        SelectionSort.sort(arr);
//        for (int i : arr) {
//            System.out.println(i);
//        }

        // insertion srot
//        int[] arr = {2, 1, 4, 6, 2, 9};
//        InsertionSort.sort(arr);
//        for (int i : arr) {
//            System.out.println(i);
//        }

        // merge sort
//        int[] arr = {2, 1, 4, 6, 2, 9};
//        MergeSort.sort(arr, 0, arr.length - 1);
//        for (int i : arr) {
//            System.out.println(i);
//        }

        // quick sort
        int[] arr = {2, 1, 4, 6, 2, 9};
        QuickSort.sort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }

    }
}
