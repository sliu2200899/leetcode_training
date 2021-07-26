package com.jetbrains.classic.topic.mathBit;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
    /*

        Let us pick up N = 3, which corresponds to the input array nums = [1, 2, 3], and construct its permutation number k = 3.
        Since we number the permutations from 0 to N! - 1 (and not from 1 to N!N! as in the problem description),
        for us that will be the permutation number k = 2.

        Let us first construct the factorial representation of k = 2;
        k=2=1×2!+0×1!+0×0!=(1,0,0)

        The coefficients in factorial representation are indexes of elements in the input array. These are not direct indexes,
        but the indexes after the removal of already used elements.

        permutation      permutation number     factorial number system representation
         2, 1, 3         2=1×2!+0×1!+0×0!           (1,0,0)

        Here the first number is 1, i.e. the first element in the permutation is nums[1] = 2.
        Let us use nums[1] = 2 in the permutation and then delete it from nums,


        time:  O(n^2)   because to delete elements from the list in a loop one has to perform N + (N - 1) + ... + 1 = N(N - 1)/2 operations.
        space:  O(n)
     */
    public String getPermutation(int n, int k) {
        int[] factorials = new int[n];

        // generate nums 1,2, ..., n
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            nums.add(i);
        }

        // generate factorial system base 0!, 1!, ..., (n-1)!
        factorials[0] = 1;
        for (int i = 1; i < n; ++i) {
            factorials[i] = factorials[i-1] * i;
        }

        --k; // fit k in the interval 0...(n! - 1)

        // compute factorial representation of k
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i > -1; --i) {
            int idx = k / factorials[i];
            k -= idx * factorials[i];

            // pick up element at index: idx, then use it in permutation and delete it from the list
            sb.append(nums.get(idx));
            nums.remove(idx);
        }

        return sb.toString();
    }
}
