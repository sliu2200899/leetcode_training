package com.jetbrains.classic.topic.design;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthProblemSummary {

    /*
        classic K-th problem.
        Here I will share some summaries and some classical solutions to this kind of problem.
        I. The very naive and simple solution is sorting the all points by their distance to the origin point directly, then get the top k closest points. We can use the sort function and the code is very short.

        Theoretically, the time complexity is O(NlogN), pratically, the real time it takes on leetcode is 104ms.

        The advantages of this solution are short, intuitive and easy to implement.
        The disadvantages of this solution are not very efficient and have to know all of the points previously, and it is unable to deal with real-time(online) case, it is an off-line solution.
    */

        public int[][] kClosest1(int[][] points, int K) {
            Arrays.sort(points, (p1, p2) -> p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]);
            return Arrays.copyOfRange(points, 0, K);
        }

    /*
        II. The second solution is based on the first one. We don't have to sort all points.
        Instead, we can maintain a max-heap with size K. Then for each point, we add it to the heap. Once the size of the heap is greater than K, we are supposed to extract one from the max heap to ensure the size of the heap is always K. Thus, the max heap is always maintain top K smallest elements from the first one to crruent one. Once the size of the heap is over its maximum capacity, it will exclude the maximum element in it, since it can not be the proper candidate anymore.

        Theoretically, the time complexity is O(NlogK), but pratically, the real time it takes on leetcode is 134ms.

        The advantage of this solution is it can deal with real-time(online) stream data. It does not have to know the size of the data previously.
        The disadvatage of this solution is it is not the most efficient solution.
    */

    public int[][] kClosest2(int[][] points, int K) {
        PriorityQueue<int[]> pqmax = new PriorityQueue<>((a, b) -> {   // maintain a pqmax with size k
            return Integer.compare(b[0] * b[0] + b[1] * b[1], a[0] * a[0] + a[1] * a[1]);
        });
        for (int[] point : points) {
            pqmax.offer(point);
            if (pqmax.size() > K) {
                pqmax.poll();
            }
        }

        int[][] res = new int[K][2];
        for (int i = K - 1; i >= 0; --i) {
            res[i] = pqmax.poll();
        }
        return res;
    }
    /*
        III. The last solution is based on quick sort, we can also call it quick select. In the quick sort, we will always choose a pivot to compare with other elements.
        After one iteration, we will get an array that all elements smaller than the pivot are on the left side of the pivot and
        all elements greater than the pivot are on the right side of the pviot (assuming we sort the array in ascending order).
        So, inspired from this, each iteration, we choose a pivot and then find the position p the pivot should be. Then we compare p with the K,
        if the p is smaller than the K, meaning the all element on the left of the pivot are all proper candidates but it is not adequate,
        we have to do the same thing on right side, and vice versa. If the p is exactly equal to the K, meaning that we've found the K-th position.
        Therefore, we just return the first K elements, since they are not greater than the pivot.

        Theoretically, the average time complexity is O(N) , but just like quick sort, in the worst case, this solution would be degenerated to O(N^2), and pratically, the real time it takes on leetcode is 15ms.

        The advantage of this solution is it is very efficient.
        The disadvantage of this solution are it is neither an online solution nor a stable one. And the K elements closest are not sorted in ascending order.

    */
    public int[][] kClosest(int[][] points, int k) {
        int len = points.length, l = 0, r = len - 1;
        while (l <= r) {
            int partition_index = partition(points, l, r);
            if (partition_index == k) break;
            if (partition_index < k) {
                l = partition_index + 1;
            } else {
                r = partition_index - 1;
            }
        }

        return Arrays.copyOfRange(points, 0, k);
    }

    private int partition(int[][] A, int l, int r) {
        int mid = l + (r - l) / 2;
        int[] pivot = A[mid];


        int end_index = r;
        swap(A, mid, r);

        while (l <= r) {
            while (l <= r && compare(A[l], pivot) < 0) l++;
            while (l <= r && compare(A[r], pivot) >= 0) r--;

            if (l <= r) {
                swap(A, l, r);
                l++;
                r--;
            }
        }

        swap(A, l, end_index);
        return l;
    }

    private void swap(int[][] A, int l, int r) {
        int[] temp = A[l];
        A[l] = A[r];
        A[r] = temp;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}
