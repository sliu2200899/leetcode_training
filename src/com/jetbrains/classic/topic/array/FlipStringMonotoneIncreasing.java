package com.jetbrains.classic.topic.array;

public class FlipStringMonotoneIncreasing {
    /*
    "00110"
    1

    rArr:  num of 0s to the right of i  [2,1,1,1,0]
    lArr:  num of 1s to the left of i   [0,0,0,1,1]

    iterate the array
    min(rArr[i] + lArr[i])

    time: O(n)
    space: O(n)
*/
    public int minFlipsMonoIncr(String S) {
        int n = S.length();
        int[] rArr = new int[n];
        int[] lArr = new int[n];

        int num = 0;
        for (int i = n - 1; i >= 0; --i) {
            rArr[i] = num;
            if (S.charAt(i) == '0') {
                num++;
            }
        }

        num = 0;
        for (int i = 0; i < n; ++i) {
            lArr[i] = num;
            if (S.charAt(i) == '1') {
                num++;
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            res = Math.min(res, rArr[i] + lArr[i]);
        }

        return res;
    }
}
