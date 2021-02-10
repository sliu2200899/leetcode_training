package com.jetbrains.classic.topic.randomAlgo;

import java.util.Random;

public class ReservoirSampling {
    /*
    Reservoir sampling is a family of randomized algorithms for randomly choosing k samples from a list of n items,
    where n is either a very large or unknown number. Typically n is large enough that the list doesn’t fit into main memory.
    For example, a list of search queries in Google and Facebook.
     */

    /*
    Given a data set of unknown size N, uniformly select k elements from the set such that each element has a 1/N probability of being chosen
     */

    static Random rand = new Random();
    public static int[] reserviorSampling(int k, int[] nums) {
        if (k >= nums.length) return nums;

        int i = 0;
        int[] rst = new int[k];
        for (; i < k; ++i) {
            rst[i] = nums[i];
        }

        for (; i < nums.length; ++i) {
            // random is exclusive
            int num = rand.nextInt(i + 1); // choose one number from [0, i]
            if (num < k) rst[num] = nums[i];
        }
        return rst;
    }
}
