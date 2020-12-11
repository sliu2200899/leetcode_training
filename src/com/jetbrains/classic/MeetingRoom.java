package com.jetbrains.classic;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoom {
    // meeting room 1: determine if a person could attend all meetings
    // look at how they analyse this problem:  https://leetcode.com/problems/meeting-rooms/solution/
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (s1, s2) -> Integer.compare(s1[0], s2[0]));
        for (int i = 0; i < intervals.length - 1; ++i) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        return true;

        // Time: O(nlogn)
        // Space: O(1)
    }


    // meeting room 2:
    //     find the minimum number of conference rooms required.

    // priority queue to solve the problem
    // time:  O(nlogn)  where n is the number of the elements in the array
    // space: O(n) because we construct the min-heap and that can contain NN elements in the worst case as described above
    // in the time complexity section.
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) return 0;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<int[]> minpq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        minpq.offer(intervals[0]);

        for (int i = 0; i < intervals.length; ++i) {
            int[] minPair = minpq.peek();
            if (minPair[1] <= intervals[i][0]) {
                minpq.poll();
            }
            minpq.offer(intervals[i]);
        }

        return minpq.size();
    }

    //Chronological ordering
    public int minMeetingRooms2(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) return 0;

        int n = intervals.length;

        Integer[] startArr = new Integer[n];
        Integer[] endArr = new Integer[n];

        for (int i = 0; i < n; ++i) {
            startArr[i] = intervals[i][0];
            endArr[i] = intervals[i][1];
        }

        // sort two array individully
        Arrays.sort(startArr, (a, b) -> Integer.compare(a, b));
        Arrays.sort(endArr, (a, b) -> Integer.compare(a, b));

        // two pointers e_ptr and s_ptr
        int startPointer = 0, endPointer = 0, usedRooms = 0;
        while (startPointer < n) {

            // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
            if (startArr[startPointer] >= endArr[endPointer]) {
                usedRooms -= 1;
                endPointer += 1;
            }

            // We do this irrespective of whether a room frees up or not.
            // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
            // remain the same in that case. If no room was free, then this would increase used_rooms
            usedRooms += 1;
            startPointer += 1;
        }

        return usedRooms;
    }
}
