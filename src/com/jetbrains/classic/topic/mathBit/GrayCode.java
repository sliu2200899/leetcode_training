package com.jetbrains.classic.topic.mathBit;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {

    /*
        Recursion
        Consider the sequence generated for n = 0 to 3.

        For n = 0 the Gray code sequence is [0].

        For n = 1 the Gray code sequence is [0, 1].

        Similarly, we had [0, 1] from n = 1 and for n = 2 the Gray code sequence is [00, 01, 11, 10].

        By observing the sequences generated with n = 0, 1, 2.... we will find a pattern. We can obtain the Gray code sequence for n from the Gray code sequence for n - 1.

        For example the Gray code sequence for n = 3 is [000, 001, 011, 010, 110, 111, 101, 100] (G(3)). This sequence can be obtained from the sequence [00, 01, 11, 10](say G(2)) for n = 2 as follows :

        1. Add 0 to the (n−1)th position (0 based indexed, the 2nd bit from the right) to the entire sequence of G(2). [00, 01, 11, 10] -> [000, 001, 011, 010] (G(3a)).

        2. Reverse G(2) sequence and add 1 (1 << n - 1) to the (n−1)th position (the 2 nd bit from the right) [00, 01, 11, 10] -> [10, 11, 01, 00] -> [110, 111 101, 100] (G(3b)).

        3. Concatenate G(3a) and G(3b) to get the Gray code sequence for n = 3 (G(3)) : [000, 001, 011, 010, 110, 111, 101, 100]


        time: O(2^n)  At every function call we iterate over the list result and at each iteration we add a new number to the sequence. At n = 0, n = 1,.... we iterate teh list of size 2 ^ (n - 1)... add them up
        space: O(n)  We start from nn and continue our recursive function call until our base condition n = 0n=0 is reached. Thus, the depth of the function call stack will be O(n)O(n).
     */
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();

        grayCodeHelper(result, n);

        return result;
    }

    private void grayCodeHelper(List<Integer> result, int n) {
        if (n == 0) {
            result.add(0);
            return;
        }

        // we derive the n bits sequence from the (n-1) bits sequence
        grayCodeHelper(result, n - 1);
        int currLen = result.size();

        // set the bit at position n - 1 (0 indexed) and assign it to mask.
        int mask = 1 << (n-1);

        for (int i = currLen - 1; i >= 0; --i) {
            // mask is used to set the (n-1)th bit from the LSB of all the numbers present in the current sequence

            result.add(result.get(i) | mask);
        }
    }
}
