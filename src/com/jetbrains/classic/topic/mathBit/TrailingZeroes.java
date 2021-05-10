package com.jetbrains.classic.topic.mathBit;

import java.math.BigInteger;

public class TrailingZeroes {
    /*
        use brute-force approach to solve the problem
        but the time:
            log 1! * log2 +
            log 2! * log3 +
            log 3! * log4 +
            log 4! * log5 +
            log 5! * log6 +
            ...
            log ((n-1)!) * log n

            time complexity is complicated
            This sequence is quite complicated to add up; instead of trying to find an exact answer, we're going to now focus on a
            rough lower bound approximation by throwing away the less significant terms. While this is not something you'd do if we
            needed to find the exact time complexity, it will allow us to quickly see that the time complexity is "too big" for our purposes.
            Often finding lower (and upper) bounds is enough to decide whether or not an algorithm is worth using. This includes in interviews!

            At this point, you'll ideally realise that the algorithm is worse than O(n), as we're adding nn terms.
            Given that the question asked us to come up with an algorithm that's no worse than O(logn), this is definitely not good enough.

            TLE...
     */

    public int trailingZeroes(int n) {
        // calculate n!
        BigInteger nFactorial = BigInteger.ONE;
        for (int i = 2; i <= n; ++i) {
            nFactorial = nFactorial.multiply(BigInteger.valueOf(i));
        }

        // count how many 0's are on teh end
        int zeroCount = 0;
        while (nFactorial.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
            nFactorial = nFactorial.divide(BigInteger.TEN);
            zeroCount++;
        }

        return zeroCount;
    }

    /*
        counting factors of 5

        time: O(n)
        space: O(1)
     */
    public int trailingZeroes2(int n) {
        int zeroCount = 0;
        for (int i = 5; i <= n; ++i) {
            int currentFactor = i;
            while (currentFactor % 5 == 0) {
                zeroCount++;
                currentFactor /= 5;
            }
        }

        return zeroCount;
    }

    /*
        counting factors of 5 efficiently

        in the above approach, we check each number whether it can divide 5 with 0 remainder,
        actually, Well, by counting the number of multiples of 55 up to nn, we're just counting how many 55s go into nn. That's the exact definition of integer division!

        so, a way of simplifying the above algo is as follows:
        fives = n / 5
        tens = fives

        how can we fix the "duplicate factors" problem? Observe that all numbers that have (at least) two factors of 5 are multiples of 25.
        like with the 5 factors, we can simply divide by 25 to find how many multiples of 25 are below n. also, notice that
        because we've already counted the multiples of 25 in n/5 once, we only need to count n/25 extra factors of 5. as this is
        one extra for each multiple of 25.

        time: O(logn)
        space: O(1)
     */
    public int trailingZeroes3(int n) {
        int zeroCount = 0;
        // we need to use long because
        long currentMultiple = 5;
        while (n >= currentMultiple) {
            zeroCount += (n / currentMultiple);
            currentMultiple *= 5;
        }

        return zeroCount;
    }
}
