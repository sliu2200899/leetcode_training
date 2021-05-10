package com.jetbrains.classic.topic.mathBit;

public class MajorityElement {
    /*
        Boyer - Moore Voting Algorithm

        If we had some way of counting instances of the majority element as +1 and instances of any other element as -1,
        summing them would make it obvious that the majority element is indeed the majority element.

        if there is definitely a majority element, then the count in the end should not be zero, which means the candidate is
        the majority element. If we're not sure if there is a majority element or not, we need to verify teh final candidate.

        time: O(n)
        space: O(1)
     */
    public int majorityElement(int[] nums) {

        int count = 1;
        int candidate = nums[0];

        for (int i = 1; i < nums.length; ++i) {

            count += ((nums[i] == candidate) ? 1 : -1);

            if (count == 0) {
                candidate = nums[i];
                count = 1;
            }
        }

        return candidate;
    }
}
