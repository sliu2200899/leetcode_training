package com.jetbrains.classic.searchByStructure.hashingSearch;

import java.util.HashMap;
import java.util.Map;

public class FourSum2 {
    /*
    clarify:
        1. input, output, example
            nums1, nums2, nums3, nums4 have the same length of n
        2. range of the input number
            output jsut the number of the combinations
    algo:
        nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]

        (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0

        Building further on this idea, we can observe that a + b == -(c + d).
        First, we will count sums of elements a + b from the first two arrays using a hashmap.
        Then, we will enumerate elements from the third and fourth arrays, and search for a complementary sum a + b == -(c + d) in the hashmap

    time: O(n^2)
    space: O(n^2)
*/
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a: nums1) {
            for (int b: nums2) {
                map.put(a+b, map.getOrDefault(a+b, 0) + 1);
            }
        }

        for (int c: nums3) {
            for (int d: nums4) {
                cnt += map.getOrDefault(-(c+d), 0);
            }
        }
        return cnt;
    }

    /*
        follow up: k sum 2
        For an interview, keep in mind the generalized implementation. Even if your interviewer is OK with a simpler code,
        you'll get some extra points by describing how your solution can handle more than 4 arrays.
     */
    public int fourSumCount2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        return kSumCount(new int[][]{nums1, nums2, nums3, nums4});
    }

    private int kSumCount(int[][] lists) {
        Map<Integer, Integer> m = new HashMap<>();
        addToHash(lists, m, 0, 0);
        return countComplements(lists, m, lists.length / 2, 0);
    }

    private void addToHash(int[][] lists, Map<Integer, Integer> m, int i, int sum) {
        if (i == lists.length / 2) {
            m.put(sum, m.getOrDefault(sum, 0) + 1);
        } else {
            for (int a : lists[i]) {
                addToHash(lists, m, i + 1, sum + a);
            }
        }
    }

    private int countComplements(int[][] lists, Map<Integer, Integer> m, int i, int complement) {
        if (i == lists.length) {
            return m.getOrDefault(complement, 0);
        }
        int cnt = 0;
        for (int a : lists[i]) {
            cnt += countComplements(lists, m, i + 1, complement - a);
        }
        return cnt;
    }
}
