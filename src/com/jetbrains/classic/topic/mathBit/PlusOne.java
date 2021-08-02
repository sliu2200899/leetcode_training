package com.jetbrains.classic.topic.mathBit;

public class PlusOne {
    /*
        time: O(N)
        space: O(N)
            Although we perform the operation in-place (i.e. on the input list itself), in the worst scenario, we would need to allocate an intermediate space to hold the result,
            which contains the N+1N+1 elements. Hence the overall space complexity of the algorithm is \mathcal{O}(N)O(N).
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;


        // move along the input array starting from the end
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                // increase this rightmost not-nine by 1
                digits[i]++;
                // and the job is done
                return digits;
            }
        }

        // we're here because all the digits are nines
        digits = new int[n+1];
        digits[0] = 1;

        return digits;
    }
}
