package com.jetbrains.zCompanyInterviewPrep.amazon.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class FindMediaFromDataStream {
    // basic one
    /*
            List<Integer> list;

            public MedianFinder() {
                list = new ArrayList<>();
            }

            public void addNum(int num) {
                list.add(num);
            }

            public double findMedian() {
                Collections.sort(list);

                int n = list.size();    // 0,1,2   3
                if ((n & 1) == 0) {
                    return (list.get(n / 2) + list.get(n / 2 - 1)) * 0.5;
                } else {
                    return list.get(n / 2) * 1.0;
                }
            }
     */

    // two heap approach

    PriorityQueue<Integer> minpq;
    PriorityQueue<Integer> maxpq;

    /** initialize your data structure here. */
    public void MedianFinder() {
        minpq = new PriorityQueue<>();
        maxpq = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {   // size of maxpq should be larger than size of the minpq
        maxpq.offer(num);
        minpq.offer(maxpq.poll());
        if (minpq.size() > maxpq.size()) {
            maxpq.offer(minpq.poll());
        }
    }

    public double findMedian() {
        if(minpq.size() == maxpq.size()) {
            return (double) (maxpq.peek() + minpq.peek()) * 0.5;
        } else {
            return (double) maxpq.peek();
        }
    }

}
