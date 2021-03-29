package com.jetbrains.classic.topic.design;

import java.util.PriorityQueue;

public class KthLargestElementsStream {
    /*
        I see that the question isn't very clear. it doesn't clarify if the stream changes with every add or not. However,
        if you look at the provided example, you would get an idea that the new values from add method add to the stream.
        If you still don't follow the example, remember it is asking for the kth highest number.
        That means if we consider a sorted array "12345" then "4" is the 2nd highest number in that array. Hope this clarifies what is going on.

        One way to solve this problem is to sort the array. However, you would need to sort the array everytime.
        Another way is to use a BST that is efficient. However, the most common and popular way is to use a heap (priority queue).
        Some folks may think about using a max priority queue for this problem since it asks for the kth max. However,
        that is a wrong choice since the max heap keeps the max value at the top and the kth max may be somewhere at the bottom of the heap.
        If you keep a min heap of size k then the kth max will stay at the top and very easy to extract. With that in mind, here is the code.

        Another benefit of using a min heap is we just need to store k elements out of n given elements from the array.
        If you are worried about loosing any elements then remember the rejected elements are not in top k so they will
        never be a candidate since we don't remember any elements.
     */
    PriorityQueue<Integer> pqmin;
    int k;
    public void KthLargest(int k, int[] nums) {
        this.k = k;
        pqmin = new PriorityQueue<>();
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        pqmin.offer(val);
        if (pqmin.size() > k) {
            pqmin.poll();
        }
        return pqmin.peek();
    }
}
