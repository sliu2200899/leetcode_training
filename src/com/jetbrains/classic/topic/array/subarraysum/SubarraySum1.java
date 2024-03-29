package com.jetbrains.classic.topic.array.subarraysum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubarraySum1 {
    /*
        Given an integer array, find a subarray where the sum of numbers is zero. Your code should return the index of the first number and the index of the last number.

        Example
        Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].
     */
    public List<Integer> subarraySum(int[] nums) {
        // write your code here

        List<Integer> list = new ArrayList<>();
        if (nums == null) return list;

        //  [-3, 1, 2, -3, 4]
        // j        i
        // map: [[0, -1], [-3, 0], [-2, 1], ]
        // sum: 0
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1);   // use hashmap to store the sum and its index...
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                list.add(map.get(sum) + 1);
                list.add(i);
                break;
            }
            map.put(sum, i);
        }
        return list;
    }

    /*
        summary:
        HashMap 在Array 题目中一般可以用O(n)的时间复杂度解决两种问题
        1，求两者之和为固定某数
          if (map.contains(sum - curtValue)) {
               index1 = map.get(sum - curtValue);
               index2 = curtIndex;
               break;
           }
        2，求两个index之间的所有数的和为某数 e.g. sum   本质上是对于prefix array 找任意两个数的差为某数
           if (map.contains(curtSum - sum)) {
               index1 = map.get(curtSum - sum);
               index2 = curtIndex;
               break;
           }
     */

    /*
        follow up: leetcode 560
        return the total number of continuous subarrays whose sum equals to k.

     */
    public int subarraySum(int[] nums, int k) {
        int currSum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            currSum += nums[i];

            // situation 1: continuous subarray that starts with the beginning of the array
            if (currSum == k) {
                count++;
            }

            // situation 2: number of times the curr_sum - k has occurred already, determines the
            // number of times a subarray with sum k has occurred upto the current index
            count += map.getOrDefault(currSum - k, 0);

            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }

        return count;
    }

}
