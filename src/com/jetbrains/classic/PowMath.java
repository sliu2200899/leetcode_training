package com.jetbrains.classic;

public class PowMath {

    // x^n  x to the power of n  (n th power)
    // x^(n/2)   x to the power of n over 2

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
}
