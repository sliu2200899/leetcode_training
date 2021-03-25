package com.jetbrains.classic.searchByStructure.sequentialSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {
    /*
        basic:  just use ArrayList
            List<Integer> list to store all the numbers
            addNum()   O(1)
            findMedian()  ->.  sort -> find median.  O(nlogn)
    */
    List<Integer> list;
    /** initialize your data structure here. */
    public void MedianFinder() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        list.add(num);
    }

    public double findMedian() {
        Collections.sort(list);

        if (list.size() % 2 == 1) {
            return 1.0 * list.get((list.size() - 1) / 2);
        }
        return 1.0 * (list.get((list.size() - 1) / 2) + list.get(list.size() / 2)) / 2;
    }

    /*
        other:
            two priorityqueue
            pqmin, pqmax

            addNum()
            check cur, pqmin.peek(), pqmax.peek()
            if cur > pqmin.peek()
                pqmin.offer(cur)
            else
                pqmax.offer(cur)


            check pqmin.size() pqmax.size()
            abs()  >= 1
            balance them

            time: O(logN)


            findMedian()
            check the size of pqmin and pqmax.
            if size is odd,
                return

            time: O(1)

     */
    PriorityQueue<Integer> pqmin;
    PriorityQueue<Integer> pqmax;
    /** initialize your data structure here. */
    public void MedianFinder2() {
        pqmin = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a, b);
        });

        pqmax = new PriorityQueue<>((a, b) -> {
            return Integer.compare(b, a);
        });
    }

    public void addNum2(int num) {
        if (pqmin.isEmpty() && pqmax.isEmpty()) {
            pqmin.offer(num);
            return;
        }

        if (!pqmin.isEmpty() && pqmin.peek() < num) {
            pqmin.offer(num);
        } else {
            pqmax.offer(num);
        }

        // rebalancing
        if (Math.abs(pqmin.size() - pqmax.size()) > 1) {
            if (pqmin.size() > pqmax.size()) {
                pqmax.offer(pqmin.poll());
            } else {
                pqmin.offer(pqmax.poll());
            }
        }
    }

    public double findMedian2() {
        int sizeMin = pqmin.size(), sizeMax = pqmax.size();
        if ((sizeMin + sizeMax) % 2 == 1) {
            return 1.0 * (sizeMin > sizeMax ? pqmin.peek() : pqmax.peek());
        } else {
            return (pqmin.peek() + pqmax.peek()) / 2.0;
        }
    }


    /*

        Follow-ups:
            refer to:  https://leetcode.com/problems/find-median-from-data-stream/discuss/1061723/Java-Solutions-For-Two-Follow-ups

            If all integer numbers from the stream are between 0 and 100, how would you optimize it?
            bucket sort, create an array of bucket of length 101, keep the count of numbers in each bucket, and the count of overall numbers,
            then it's easy to locate the bucket where the median number resides and find the median by looping through the array-> O(1)

            If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
            99% of all integer numbers lie in [0,100] means the median is definitely inside [0,100]. Besides the bucket range [0,100] as mentioned above,
            we just need to keep the count of all numbers, numbers smaller than 0, and numbers larger than 100, and adjust the index of the median element in our buckets of range [0,100].
            For example, if we have 100 numbers streamed, and one of the number is smaller than 0, that means the median's index moved forward by 1,
            we just need to find the 49th element in our buckets in a sorted manner.
     */
    /*
        1. If all integer numbers from the stream are between 0 and 100, how would you optimize it?

        We can use an array with 100 slots to store the count of each number, and then iterate through the array to find the median.
        As the size of the array is 101, the time complexity of finding the median is constant.
     */
    int[] arr;
    int size;
    /** initialize your data structure here. */
    public void MedianFinder3() {
        arr = new int[101];
        size = 0;
    }

    public void addNum3(int num) {
        arr[num]++;
        size++;
    }

    public double findMedian3() {
        int index = 0, median = size / 2, first = 0;
        for(int i = 0; i < 101; i++) {
            for(int j = 0; j < arr[i]; j++) {
                if(index == median - 1) first = i;
                else if(index == median) {
                    if(size % 2 == 0) return (first + i) / 2.0;
                    else return (double) i;
                }
                index++;
            }
        }
        return -1;
    }

    /*
        2. If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?

        It's similar to above, but here are situations we need to talk about.

        If we can make sure we will call findMedian() only when we already have most of integer numbers inserted,
        which assure the median would appear between 0 and 100, we can just use a variable to store the total size of all
        numbers and the number of elements that are less than 0 respectively and don't have to care about what those numbers are.
        For those numbers that are greater than 100, we don't have to really care about them to find median as we will iterate through from the lowest number.
     */
//    int[] arr;
//    int size;
    int lo;
    /** initialize your data structure here. */
    public void MedianFinder4() {
        arr = new int[101];
        size = 0;
        lo = 0;
    }

    public void addNum4(int num) {
        if(num < 0) lo++;
        else if(num <= 100 && num >= 0) arr[num]++;
        size++;
    }

    public double findMedian4() {
        int index = lo, median = size / 2, first = 0;
        for(int i = 0; i < 101; i++) {
            for(int j = 0; j < arr[i]; j++) {
                if(index == median - 1) first = i;
                else if(index == median) {
                    if(size % 2 == 0) return (first + i) / 2.0;
                    else return (double) i;
                }
                index++;
            }
        }
        return -1;
    }
}
