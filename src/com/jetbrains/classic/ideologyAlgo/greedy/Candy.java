package com.jetbrains.classic.ideologyAlgo.greedy;

import java.util.Arrays;

public class Candy {
    /*
    clarify:
        1. input, output, example
        2. rules
    algo:
        ratings = [1,0,2]

        extra array of candies
        1,      1,      2,
        1,      0,      2

        iterate over ratings from start to end
        ratings[i] > ratings[i-1]    nums[i] = nums[i-1] + 1

        iterate over ratings from end to start
        ratings[i] > ratings[i+1]    nums[i] = nums[i+1] + 1

        1,      2,      3,      4,      1
        1,      3,      4,      5,      2

        Instead of this, we can make use of a single array candies to keep the count of the number of candies to be allocated to the current student.
        In order to do so, firstly we assign 1 candy to each student. Then, we traverse the array from left-to-right and distribute the candies
        following only the left neighbor relation i.e. whenever the current element's ratings is larger than the left neighbor and has less than or
        equal candies than its left neighbor, we update the current element's candies in the candies array as candies[i] = candies[i-1] + 1.
        While updating we need not compare candies[i] and candies[i - 1], since candies[i] <= candies[i - 1] before updating. After this, we traverse
        the array from right-to-left. Now, we need to update the i'th element's candies in order to satisfy both the left neighbor and the right neighbor relation.

       time: O(n)
       space: O(n)
*/
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        for (int i = 1; i < candies.length; ++i) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = candies[i-1] + 1;
            }
        }

        for (int i = candies.length - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i+1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i+1] + 1;
            }
        }

        int ans = 0;
        for (int i : candies) ans += i;
        return ans;
    }
}
