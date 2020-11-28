package com.jetbrains.hard;

public class DivideTwoIntegers {
    // brute force method  TLE
    public int divide(int dividend, int divisor) {

        // Special case: overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Count the nunmber of negatives + convert parameters to positives
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend;
        }

        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }

        // Count the number of subtractions
        int quotient = 0;
        while (dividend - divisor <= 0) {
            quotient--;
            dividend -= divisor;
        }

        // Convert back to negative if needed.
        if (negatives != 1) {
            quotient = -quotient;
        }
        return quotient;
    }

    // linear search is too slow, we can speed up the search by doubling the amount
    private static int HALF_INT_MIN = -1073741824;

    public int divide2(int dividend, int divisor) {

        // Special case: overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Count the nunmber of negatives + convert parameters to positives
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend;
        }

        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }

        // Count the number of subtractions
        int quotient = 0;
        while (divisor >= dividend) {
            int powerOfTwo = -1;
            int value = divisor;

            while (value >= HALF_INT_MIN && value + value >= dividend) {
                value += value;
                powerOfTwo += powerOfTwo;
            }

            quotient += powerOfTwo;

            dividend -= value;
        }

        // Convert back to negative if needed.
        if (negatives != 1) {
            quotient = -quotient;
        }
        return quotient;
    }

    // some code patterns
    private int exponentialSearch(int dividend, int divisor) {
        int quotient = 0;
        /* Once the divisor is bigger than the current dividend,
         * we can't fit any more copies of the divisor into it. */
        while (dividend >= divisor) {
            /* Now that we're in the loop, we know it'll fit at least once as
             * divivend >= divisor */
            int powerOfTwo = 1;
            int value = quotient;
            /* Check if double the current value is too big. If not, continue doubling.
             * If it is too big, stop doubling and continue with the next step */
            while (value + value < dividend) {
                value += value;
                powerOfTwo += powerOfTwo;
            }
            // We have been able to subtract divisor another powerOfTwo times.
            quotient += powerOfTwo;
            // Remove value so far so that we can continue the process with remainder.
            dividend -= value;
        }

        return quotient;
    }
}
