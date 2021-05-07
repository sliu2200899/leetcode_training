package com.jetbrains.classic.topic.mathBit;

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurring {
    /*
        some math terms:
            GCD: greatest common divisor
                The greatest common divisor (also known as greatest common factor, highest common divisor or highest common factor) of a
                set of numbers is the largest positive integer number that devides all the numbers in the set without remainder.
                It is the biggest multiple of all numbers in the set.

                calculated:
                    gcd(a, b) = |a * b| / lcm(a, b)
            LCM:
                lowest common denominator or least common denominator (abbreviated LCD) is the lowest common multiple of the denominators of a set of fractions.
                 least common multiple of two integers a and b, usually denoted by lcm(a, b), is the smallest positive integer that is divisible by both a and b

                 calculated:
                    refer to:  https://www.calculatorsoup.com/calculators/math/lcm.php

            the key point is how to deal with the repeating fractions
            using long division
            You will need a hash table that maps from the remainder to its position of the fractional part.
            Once you found a repeating remainder, you may enclose the reoccurring fractional part with parentheses by consulting the position from the table.

            The remainder could be zero while doing the division. That means there is no repeating fractional part and you should stop right away.
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        // If either one is negative (not both)
        if (numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }
        // Convert to Long or else abs(-2147483648) overflows
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        fraction.append(String.valueOf(dividend / divisor));
        long remainder = dividend % divisor;
        if (remainder == 0) {
            return fraction.toString();
        }
        fraction.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            map.put(remainder, fraction.length());
            remainder *= 10;
            fraction.append(String.valueOf(remainder / divisor));
            remainder %= divisor;
        }
        return fraction.toString();
    }
}
