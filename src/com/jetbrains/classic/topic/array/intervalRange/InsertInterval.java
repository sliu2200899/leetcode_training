package com.jetbrains.classic.topic.array.intervalRange;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class InsertInterval {
    /*
        basic idea: using heap
        time: O(nlogn)
        space: O(n)
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {

        PriorityQueue<int[]> pqmin = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pqmin.offer(newInterval);
        for (int[] interval : intervals) {
            pqmin.offer(interval);
        }

        List<int[]> res = new ArrayList<>();
        while (!pqmin.isEmpty()) {
            int[] cur = pqmin.poll();
            if (res.isEmpty() || cur[0] > res.get(res.size() - 1)[1]) {
                res.add(cur);
            } else {
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], cur[1]);
            }
        }

        return res.toArray(new int[res.size() - 1][2]);
    }

    /*
        greedy
        Here is the algorithm :

            Add to the output all the intervals starting before newInterval.
            compare newStart with end time of each interval

            Add to the output newInterval. Merge it with the last added interval if newInterval starts before the last added interval.
            compare newEnd with start time of each interval

            Add the next intervals one by one. Merge with the last added interval if the current interval starts before the last added interval.
     */
    public int[][] insert2(int[][] intervals, int[] newInterval) {

        int newStart = newInterval[0], newEnd = newInterval[1];
        int i = 0, n = intervals.length;
        List<int[]> res = new ArrayList<>();

        // add all intervals before newInterval
        while (i < n && newStart > intervals[i][1]) {
            res.add(intervals[i++]);
        }

        // merge newInterval
        int[] interval = new int[2];
        while (i < n && intervals[i][0] <= newEnd) {
            newStart = Math.min(newStart, intervals[i][0]);
            newEnd = Math.max(newEnd, intervals[i][1]);
            ++i;
        }

        res.add(new int[]{newStart, newEnd});

        // add all intervals after newInterval
        while (i < n) {
            res.add(intervals[i++]);
        }

        return res.toArray(new int[res.size()][2]);

    }
}
