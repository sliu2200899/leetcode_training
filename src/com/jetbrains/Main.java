package com.jetbrains;

//import com.jetbrains.OOD.Elevator;

import com.jetbrains.classic.dsGraph.BasicGraphBFS;
import com.jetbrains.classic.dsGraph.BasicGraphDFS;
import com.jetbrains.classic.dsGraph.ValidTree;
import com.jetbrains.hard.ReversePairs;
import com.jetbrains.innerStructure.Graph;
import com.jetbrains.master.MaxOperations;

import java.util.Random;

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
//        int[] arr = {2, 1, 3, 6, 1, 3, 5};
//        QuickSort.quick_sort(arr, 0, arr.length - 1);
//        for (int i : arr) {
//            System.out.println(i);
//        }

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

        //findKthLargest2
//        int[] nums = {1,2,3,4,5,6};
//        FindKLargest a = new FindKLargest();
//        int val = a.findKthLargest3(nums, 3);
//        System.out.println(val);

        // skip list
//        SkipList2 sl = new SkipList2();
//        sl.insert2(4);
//        sl.insert2(9);
//        sl.insert2(1);
//        sl.insert2(2);
//
//        sl.printAll_beautiful();

//        // insertion sort
//        int[] nums = {4,2,3,6,1,2,9};
//        InsertionSort.sort2(nums);
//        for (int i : nums) {
//            System.out.println(i);
//        }

        // mergeSort 2
//        int[] nums = {4, 5, 4, 9, 4, 5};  // [0, 9]
//        CountingSort.sort(nums, 9);
//        for (int i : nums) {
//            System.out.println(i);
//        }

        // bucket sort
//        float[] arr = { (float)0.897, (float)0.565,
//                (float)0.656, (float)0.1234,
//                (float)0.665, (float)0.3434 };
//
//        int n = arr.length;
//        BucketSort.sort(arr, n);
//
//        System.out.println("Sorted array is ");
//        for (float el : arr) {
//            System.out.print(el + " ");
//        }

        //ReversePlus
//        ListNode l1 = new ListNode(9);
//        l1.next = new ListNode(9);
//        l1.next.next = new ListNode(9);
//
//        ReversePlus r = new ReversePlus();
//        ListNode tr = r.plus(l1);
//        while (tr != null) {
//            System.out.println(tr.val);
//            tr = tr.next;
//        }

        //CombinationSum
//        int[] nums = {2, 3, 6, 7};
//        CombinationSum cs = new CombinationSum();
//        List<List<Integer>> res = cs.combinationSum(nums, 7);
//        for (List<Integer> item: res) {
//            System.out.println(item);
//        }

        // random numbers
//        int[] count = new int[10];
//
//        System.out.println((int)(Math.random() * 30 + 1));
//        Random r = new Random(1);
//        for (int i = 0; i < 6; i++) {
//            System.out.print(r.nextInt(10) +  "\t");
//        }



//        for (int i = 0; i < 10000; ++i) {
//            int[] sampled = ReservoirSampling.reserviorSampling(5, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
//            for (int num : sampled) count[num]++;
//        }
//
//        for (int i = 0; i < 10; ++i) {
//            System.out.println("Count of " + i + " " + count[i] + " times");
//        }

        // graph bfs
//        Graph g = new Graph(4);
//        g.addEdge(0, 2);
//        g.addEdge(1, 2);
//        g.addEdge(1, 3);
//        BasicGraphBFS gBFS = new BasicGraphBFS();
//        gBFS.bfs(2, 3, g.numVertices(), g.getAdj());

        // graph dfs
//        Graph g = new Graph(4);
//        g.addEdge(0, 2);
//        g.addEdge(1, 2);
//        g.addEdge(1, 3);
//        BasicGraphDFS gDFS = new BasicGraphDFS();
//        gDFS.dfs(2, 3, g.numVertices(), g.getAdj());

        // reverse pairs
//        int[] nums = {2,4,1,3,5};
//        int[] nums = {-1, -2};
//        int[] nums = {4,3,2,1};
//        ReversePairs rp = new ReversePairs();
//        rp.reversePairs2(nums);
//        System.out.println(rp.getCount());

        // graph valid tree
//        ValidTree vt = new ValidTree();
//        int[][] edges = {{0, 1}, {0,2}, {0,3}, {1,4}};
//        System.out.println(vt.validTree(5, edges));

        //max operations
//        int[] nums = {3,1,3,4,3};
//        MaxOperations m = new MaxOperations();
//        System.out.println(m.maxOperations(nums, 6));

        // quick select
        int[]
    }
}
