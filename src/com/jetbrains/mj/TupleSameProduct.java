package com.jetbrains.mj;

import java.util.HashMap;
import java.util.Map;

public class TupleSameProduct {
    public int tupleSameProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;

        int rst = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int product = nums[i] * nums[j];
                if (!map.containsKey(product)) {
                    map.put(product, 1);
                } else {
                    // most important, means added pairs(i,j) can be added with other 2-num pair to form a valid 4-num pair
                    /*
                        we have [2,6] and product is 12, next round when we got [3,4], that means we can form a valid pair like [2,3,4,6]
                        we have [2,6], [3,4] and product is 12, next round when we got [1,12], that means [1,12] can be with [2,6]   or   [1,12] can be with [3,4]

                     */
                    rst += map.get(product);
                    map.put(product, map.get(product) + 1);
                }
            }
        }
        return rst * 8;    //  this is straightforward, each 4-num pairs can be brought 8 permutations 2^3
    }
}
