package com.jetbrains.classic.topic.mathBit;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {
    /*
        single number 1
     */
    // using the hash map
    public int singleNumber(int[] nums) {
        // single number
        if (nums == null || nums.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for (int num : nums) {
            if (map.get(num) == 1) {
                return num;
            }
        }
        return -1;
    }


    // using bit operation to achieve space complexity O(1)
    // x ^ x == 0
    // x ^ 0 = x
    // associative: x ^ (y ^ z) == (x ^ y) ^z
    // commutative: x ^ y == y ^ x
    public int singleNumber2(int[] nums) {
        // single number
        if (nums == null || nums.length == 0) return 0;

        int a = 0;
        for (int num : nums) {
            a ^= num;
        }
        return a;
    }

    /*
        single number 2
        Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.

        The usual bit manipulation code is bit hard to get and replicate. I like to think about the number in 32 bits and just count how many 1s are there in each bit,
        and sum %= 3 will clear it once it reaches 3. After running for all the numbers for each bit, if we have a 1, then that 1 belongs to the single number,
        we can simply move it back to its spot by doing ans |= sum << i;

        This has complexity of O(32n), which is essentially O(n) and very easy to think and implement. Plus, you get a general solution for any times of occurrence.
        Say all the numbers have 5 times, just do sum %= 5.
     */
    public int singleNumber3(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int sum = 0;
            for (int j = 0; j < nums.length; ++j) {
                if (((nums[j] >> i) & 1) == 1) {
                    sum++;
                    sum %= 3;
                }
            }

            if (sum != 0) {
                ans |= sum << i;
            }
        }

        return ans;
    }
}
