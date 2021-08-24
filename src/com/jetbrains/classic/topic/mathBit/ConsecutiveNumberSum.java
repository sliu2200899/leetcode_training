package com.jetbrains.classic.topic.mathBit;

public class ConsecutiveNumberSum {
    /*
     N = (x + 1) + ... + (x + k)
     15 = (14 + 1)
     15 = 7 + 8 = (6 + 1) + (6 + 2)
     15 = 4 + 5 + 6 = (3 + 1) + (3 + 2) + (3 + 3)
     15 = 1 + 2 + 3 + 4 + 5 = (0 + 1) + (0 + 2) + (0 + 3) + (0 + 4) + (0 + 5)

     N = xk + (1+2+..+k)

     N = xk + k * (k + 1) / 2

     2N = k (2x + k + 1)
     the problem has been reduced to the integer Factorisation problem

     based on the above formular, let's derive x

     x = N/k - (k + 1) / 2

     two constraints:
    .   1. x should be greater or equal to zero
        2. x should be an integer

     the first constraint is easy to apply using some technique
        k <= (2N + 1/4) ^ (1/2) - 1/2

        The first constraint sets the upper boundary for kk. The second constraint, xx should be an integer, can be verified during the iteration over kk.
    */

    public int consecutiveNumbersSum(int N) {
        int count = 0;
        // x > 0 --> N/k - (k+1)/2 > 0
        int upper_limit = (int)(Math.sqrt(2*N + 0.25) - 0.5);
        for (int k = 1; k <= upper_limit; ++k) {
            // x should be an integer
            if ((N - k * (k+1) / 2) % k == 0) {
                count++;
            }
        }
        return count;
    }
}
