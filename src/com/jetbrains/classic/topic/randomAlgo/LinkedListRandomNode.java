package com.jetbrains.classic.topic.randomAlgo;

import com.jetbrains.innerStructure.ListNode;

import java.util.ArrayList;
import java.util.Random;

public class LinkedListRandomNode {
    /*
        method 1: Fixed-Range Sampling
        the problem could be as simple as choosing a random sample from a list, which in our case happens to be a linked list.
        If we are given an array or a linked list with a known size, then it would be a no brainer to solve the problem.

        Algorithm

        We are asked to implement two interfaces in the object, namely the init(head) and getRandom() functions.

        The init(head) function would be invoked once when we construct the object. Within which, intuitively we could
        convert the given linked list into an array for later reuse.

        Concerning the getRandom() function, with the linked list converted into an array, we could simply sample from this array.
     */

    /*
        time complexity:
            For the init(head) function, its time complexity is O(N) where NN is the number of elements in the linked list.
            For the getRandom() function, its time complexity is O(1). The reason is two-fold:
                it takes a constant time to generate a random number and
                the access to any element in the array is of constant time as well.
        space complexity:
            The overall solution requires O(N) space complexity, since we make a copy of elements from the input list.
     */
    private ArrayList<Integer> range = new ArrayList<>();

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public void Solution(ListNode head) {
        while (head != null) {
            this.range.add(head.val);
            head = head.next;
        }
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int pick = (int)(Math.random() * this.range.size());
        return this.range.get(pick);
    }


    /*
        method 2: Reservoir Sampling
        follow up
            The above solution is simple, which happens to be fast as well. But it comes with two caveats:

                It requires some space to keep the pool of elements for sampling, which does not meet the constraint asked
                in the follow-up question, i.e. a solution of constant space.

                It cannot cope with the scenario that we have a list with ever-growing elements, i.e. we don't have unlimited
                memory to hold all the elements. For example, we have a stream of numbers, and we would like to pick a random
                number at any given moment. With the above naive solution, we would have to keep all the numbers in the memory,
                which is not scalable.
     */

    /*
        time:
            for the init function, O(1)
            for the getRandom2 function, O(n), where n is hte number of the linked list
        space: O(1)
     */
    private ListNode head;
    private Random rand;

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public void Solution2(ListNode head) {
        this.head = head;
        rand = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom2() {
        ListNode cur = head.next;
        ListNode rst = head;
        int count = 2;
        while (cur != null) {
            int num = rand.nextInt(count++);   // [0, 1]   , count = 3
            if (num == 0) rst = cur;  // replace it
            cur = cur.next;
        }
        return rst.val;
    }
}
