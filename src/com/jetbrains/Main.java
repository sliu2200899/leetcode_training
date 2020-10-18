package com.jetbrains;

//import com.jetbrains.OOD.Elevator;
import com.jetbrains.classic.LoopArrayQueue;
import com.jetbrains.classic.sorting.BubbleSort;
import com.jetbrains.classic.sorting.CountingSort;
import com.jetbrains.classic.sorting.MergeSort;
import com.jetbrains.classic.sorting.QuickSort;
import com.jetbrains.master.StackOfArray;

public class Main {

    //math english:  https://language.chinadaily.com.cn/2016-02/24/content_23607321.htm


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
//        MergeSort.sort(arr);
//        for (int i : arr) {
//            System.out.println(i);
//        }

        // quick sort
        int[] arr = {2, 1, 3, 6, 1, 3, 5};
        QuickSort.quick_sort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }

        // merge two sorted array (basic, no enough space for storing)
//        int[] arrA = {1,4,7,9};
//        int[] arrB = {7, 20, 100};
//        int[] out = MergeSortedArray.mergeTwoSortedArray(arrA, arrB);
//        for (int i : out) {
//            System.out.println(i);
//        }

        // sort color
//        int[] nums = {0,2,2,1,0,2,1};
//        SortColor.sortColors(nums);
//        for (int i : nums) {
//            System.out.println(i);
//        }

        // counting sort
//        int[] nums = {0,2,2,1,0,2,1};
//        CountingSort.sort(nums, 3);
//        for (int i : nums) {
//            System.out.println(i);
//        }

        // maxSubArray
//        int[] nums = {0,-2,-2,-1,0,2,1,-5};
//        int res = MaxSubArray.maxSubArray(nums);
//        System.out.println(res);

        // minSunArray
//        int[] nums = {0,-2,-2,-1,0,2,1,-5};
//        int res = MinSubArray.minSubArray2(nums);
//        System.out.println(res);

        // lowestCommonAncester3
//        TreeNodeParent root = new TreeNodeParent(3);
//        root.left = new TreeNodeParent(2);
//        root.right = new TreeNodeParent(5);
//        root.parent = null;
//        root.left.left = new TreeNodeParent(4);
//        root.right.left = new TreeNodeParent(7);
//        root.left.parent = root;
//        root.right.parent = root;
//        root.left.left.parent = root.left;
//        root.right.left.parent = root.right;


//        TreeNodeParent p = LCAonBT.lowestCommonAncester3(root, root.left, root.right.left);

        //OOD
//        Elevator e = new Elevator();
//
//        System.out.println(e.sum(3.0, 5.8));

        // lastPositionOfTarget
//        int[] res = {1,2,2,5,5,6};
//        LastPositionOfTarget p = new LastPositionOfTarget();
//        System.out.println(p.lastPosition(res, 5));

        // Stack®
//        StackOfArray<String> s = new StackOfArray<>();
//        s.push("aa");
//        s.push("bb");
//        s.push("cc");
//        s.push("dd");
//        System.out.println(s.size());
//
//        s.push("hello");
//        System.out.println(s.pop());
//
//        for(String i : s) {
//            System.out.println(i);
//        }

        // Queue
//        LoopArrayQueue s = new LoopArrayQueue(3);
//        System.out.println(s.enqueue("Orange"));
//        System.out.println(s.enqueue("Apple"));;
//        System.out.println(s.enqueue("Time"));;
//        System.out.println(s.enqueue("Banana"));;
//
//        System.out.println(s.dequeue());
//
//        s.printAllData();

    }
}
