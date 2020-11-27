package com.jetbrains.classic;

public class PowMath {

    // x^n  x to the power of n  (n th power)
    // x^(n/2)   x to the power of n over 2
    // we can use the binary representation of n to better understand the problem.
    /*
        It seems to have no differences with this representation. But with this formula,
         (x ^ n) ^ 2 = x ^ (2 * n)      we can see some difference.
        Initially x^1 = x, and for each i > 1, we can use the result of x ^ (2 ^ (i - 1)) to get x ^ (2 ^ i)
        in one step. Since the number of b_i is at most O(log n), we can get all x ^ (2 ^ i) in O(log n) time.
        After that, for all i that satisfy b_i = 1, we can multiply x ^ (2 ^ i) to the result.
        This also requires O(log n) time.
     */
    public double myPow(double x, int n) {
        long abs = Math.abs((long)n);
        double base = x, res = 1.0;
        while (abs > 0) {
            if ((abs & 1) > 0) {
                res *= base;
            }
            base *= base;
            abs = abs >> 1;
        }
        return n > 0 ? res : 1 / res;
    }

    //or we can use DC to do the problem
    public double myPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    private double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    // intuition
    /*
        if n < 0, we can substitute x, n with 1/x, -n to make sure n >= 0. This restriction can simplify our
            further discussion.

        Time complexity: O(n) We will multiply x for n times
        Space complexity: O(1) We only need one variable to store the final product of x.
     */
    public double myPow3(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        double ans = 1;
        for (long i = 0; i < N; ++i) {
            ans = ans * x;
        }
        return ans;
    }
}
