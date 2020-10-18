package com.jetbrains.classic;

import java.util.Arrays;
import java.util.HashMap;

public class ArrayIntersection2 {
    /*
        approach 1 : Hashmap
        time: O(n + m)
        space: min(n, m)

         we used a hash set to achieve a linear time complexity. Here, we need to use a hash map to track the count for each number.

         We collect numbers and their counts from one of the arrays into a hash map. Then, we iterate along the second array,
         and check if the number exists in the hash map and its count is positive. If so - add the number to the result and
         decrease its count in the hash map.

         It's a good idea to check array sizes and use a hash map for the smaller array. It will reduce memory usage when one of
          the arrays is very large.


     */
    public int[] intersect(int[] nums1, int[] nums2) {

        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums1) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int k = 0;
        int[] res = new int[nums1.length];
        for (int n : nums2) {
            int cnt = map.getOrDefault(n, 0);
            if (cnt > 0) {
                res[k++] = n;
                map.put(n, cnt - 1);
            }
        }
        return Arrays.copyOf(res, k);
    }

    /*
        follow up: if we can assume the arrays are sorted, how to optimize the algo?
        Time Complexity: O(nlogn+mlogm), where nn and mm are the lengths of the arrays.
                            We sort two arrays independently, and then do a linear scan.

     */

    public int[] intersect2(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0, k = 0;
        int len1 = nums1.length, len2 = nums2.length;
        int[] res = new int[Math.min(len1, len2)];  // List<Integer> res = new ArrayList<>();
        while (i < len1 && j < len2) {
            if (nums1[i] == nums2[j]) {
                res[k++] = nums1[i];     // res.add(nums1[i]);
                i++;
                j++;
            }
            else if (nums1[i] > nums2[j]) {
                j++;
            }
            else {
                i++;
            }
        }
        return Arrays.copyOf(res, k);   // res.stream().mapToInt(x -> x).toArray();
    }
    /*
        follow up:
        2. What if nums1's size is small compared to nums2's size? Which algorithm is better?

        Approach 1 is a good choice here as we use a hash map for the smaller array.
     */

    /*
        follow up:
        3. What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

        If nums1 fits into the memory, we can use Approach 1 to collect counts for nums1 into a hash map. Then, we can sequentially load and process nums2.

        If neither of the arrays fit into the memory, we can apply some partial processing strategies:

        Split the numeric range into subranges that fits into the memory. Modify Approach 1 to collect counts only within a given subrange, and call the method multiple times (for each subrange).

        Use an external sort for both arrays. Modify Approach 2 to load and process arrays sequentially.
     */
}
