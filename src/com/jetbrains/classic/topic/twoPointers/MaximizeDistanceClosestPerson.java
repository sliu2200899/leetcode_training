package com.jetbrains.classic.topic.twoPointers;

public class MaximizeDistanceClosestPerson {
    public int maxDistToClosest(int[] seats) {
        /*

            seats = [1,0,0,0,1,0,1]
            2

            maintain 2 dp array: lArr and rArr
            Let left[i] be the distance from seat i to the closest person sitting to the left of i. Similarly, let right[i] be the distance to the closest person sitting to the right of i.
            This is motivated by the idea that the closest person in seat i sits a distance min(left[i], right[i]) away.

            iterate from left to right
            lArr: [0,1,2,3,0,1,0]
            rArr: [0,3,2,1,0,1,0]

            traverse from left to right
                dis = min(lArr[i], rArr[i])   // get the distance to the closest person
                res = max(res, dis)   // get the maximum distance

            time: O(n)
            space: O(n)
        */
        if (seats == null || seats.length == 0) return 0;

        int n = seats.length;

        int[] lArr = new int[n];
        int[] rArr = new int[n];

        int index = -1;
        for (int i = 0; i < n; ++i) {
            if (seats[i] == 1) {
                index = i;
                lArr[i] = 0;
            } else if (index == -1) {
                lArr[i] = Integer.MAX_VALUE;
            } else {
                lArr[i] = i - index;
            }
        }

        index = -1;
        for (int i = n - 1; i >= 0; --i) {
            if (seats[i] == 1) {
                index = i;
                rArr[i] = 0;
            } else if (index == -1) {
                rArr[i] = Integer.MAX_VALUE;
            } else {
                rArr[i] = index - i;
            }
        }

        int res = 0;
        for (int i = 0; i < n; ++i) {
            int dis = Math.min(lArr[i], rArr[i]);
            res = Math.max(res, dis);
        }

        return res;
    }


    /*

        use two pointers

            time: O(n)
            space: O(1)
     */
    public int maxDistToClosest2(int[] seats) {
        if(seats == null || seats.length == 0) return -1;
        int left = -1, right = -1;
        int len = seats.length;
        int dist = 0;

        int i = 0;
        while(i < len) {
            // while 1
            while (i < len && seats[i] == 1) {
                i++;
            }
            left = i;
            //while 0
            while(i < len && seats[i] == 0) {
                i++;
            }
            right = i;
            // if start index 0 or end last,dist is (right - left)
            // or dist is Math.ceil((right - left) / 2.0)
            if(left == 0 || right == len) {
                dist = Math.max(right - left, dist);
            } else {
                dist = Math.max((int) Math.ceil((right - left) / 2.0), dist);
            }
        }
        return dist;
    }
}
