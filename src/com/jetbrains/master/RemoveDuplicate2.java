package com.jetbrains.master;

public class RemoveDuplicate2 {
    /*
        algo:
            We define two pointers, i and j for our algorithm. The pointer i iterates of the array processing one
            element at a time and j keeps track of the next location in the array where we can overwrite an element.

            We also keep a variable count which keeps track of the count of a particular element in the array.
            Note that the minimum count would always be 1.

            We start with index 1 and process one element at a time in the array

            If we find that the current element is the same as the previous element i.e. nums[i] == nums[i - 1],
            then we increment the count. If the value of count > 2, then we have encountered an unwanted duplicate element.
            In this case, we simply move forward i.e. we increment i but not j.

            However, if the count is <= 2, then we can move the element from index i to index j.

        time: O(N)
        space: O(1)

     */
    public int removeDuplicates(int[] nums) {
        int idx = 1;
        int count = 1;
        // start from the second element of the array and process elements one by one
        for (int i = 1; i < nums.length; ++i) {

            // if the current element is a duplicate, increment the count
            if (nums[i] == nums[i - 1]) {
                count++;
            }
            else {
                // reset the count since we encountered a different element than the previous one.
                count = 1;
            }

            if (count <= 2) {
                nums[idx++] = nums[i];
            }
        }

        return idx;
    }
}
