package com.jetbrains.classic.topic.array.intervalRange;

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
           /*
            analyze:
                intervals = [[0,30],[5,10],[15,20]]
                2

                0.                    30
                   5.  10
                           15.   20



                firstly, sort the intervals array based on starting time of each interval
                secondly, j tracks the number of conference rooms required init as 0
                iterate teh intervals array and compare ending time of cur interval with starting time of previous interval
                if exist some overlapping, increment teh j

            algo:
                Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

                minpq sort by Integer.compare(a[1], b[1])

                minpq.offer(intervals[0])

                for int i = 1; i < intervals.length; ++i
                    int[] minPair = pqmin.peek()
                    if (minPair[1] <= intervals[i][0])
                        minpq.poll()

                    minpq.offer(intervals[i])

                return minpq.size();

        */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) return 0;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<int[]> minpq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        minpq.offer(intervals[0]);

        for (int i = 1; i < intervals.length; ++i) {
            int[] minPair = minpq.peek();
            if (minPair[1] <= intervals[i][0]) {
                minpq.poll();
            }
            minpq.offer(intervals[i]);
        }

        return minpq.size();
    }

    //Chronological ordering
    /*
    algo:
        1.Separate out the start times and the end times in their separate arrays.
        2.Sort the start times and the end times separately. Note that this will mess up the original correspondence of start times and end times.
          They will be treated individually now.
        3.We consider two pointers: s_ptr and e_ptr which refer to start pointer and end pointer.
          The start pointer simply iterates over all the meetings and the end pointer helps us track if a meeting has ended and if we can reuse a room.
        4.When considering a specific meeting pointed to by s_ptr, we check if this start timing is greater than the meeting pointed to by e_ptr.
          If this is the case then that would mean some meeting has ended by the time the meeting at s_ptr had to start.
          So we can reuse one of the rooms. Otherwise, we have to allocate a new room.
        5.If a meeting has indeed ended i.e. if start[s_ptr] >= end[e_ptr], then we increment e_ptr.
        6.Repeat this process until s_ptr processes all of the meetings.

     */
    public int minMeetingRooms2(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) return 0;

        int n = intervals.length;

        int[] startArr = new int[n];
        int[] endArr = new int[n];

        for (int i = 0; i < n; ++i) {
            startArr[i] = intervals[i][0];
            endArr[i] = intervals[i][1];
        }

        // sort two array individully
        Arrays.sort(startArr);
        Arrays.sort(endArr);

        int num = 0, sp = 0, ep = 0;
        while (sp < n) {
            // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
            if (startArr[sp] < endArr[ep]) {
                num++;
                sp++;
            } else {
                // We do this irrespective of whether a room frees up or not.
                // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
                // remain the same in that case. If no room was free, then this would increase used_rooms
                num--;
                ep++;

                num++;
                sp++;

            }
        }
        return num;
    }
}
