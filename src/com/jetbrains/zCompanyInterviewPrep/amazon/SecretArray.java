package com.jetbrains.zCompanyInterviewPrep.amazon;

public class SecretArray {
    // An array is said to be analogous to the secret array if all of the following conditions are true:
    // • The length of the array is equal to the length of the secret array.
    // • Each integer in the array lies in the interval [lowerBound, upperBound].
    // • The difference between each pair of consecutive integers of the array must be equal to the difference between the respective pair of consecutive integers in the secret array. In other words, let the secret array be [s[0], s[1],..., s[n-1]] and let the analogous array be [a[0], a[1],..., a[n-1]], then (a[i-1] - a[i]) must be equal to (s[i-1] - s[i]) for each i from 1 to n -1.

    // Given the value of integers lowerBound and upperBound, inclusive, and the array of differences between each pair of consecutive integers of the secret array, find the number of arrays that are analogous to the secret array. If there is no array analogous to the secret array, return 0.

    // For example:
    // consecutiveDifference = [-2, -1, -2, 5]
    // lowerBound = 3
    // upperBound = 10

    // Note that none of the values is out of the bound. All possible analogous arrays are:
    // [3, 5, 6, 8, 3]
    // [4, 6, 7, 9, 4]
    // [5, 7, 8, 10, 5]

    // Tha answer is 3.
    public static int secretArray(int[] input, int low, int high) {
        int lowest = Integer.MAX_VALUE;
        int highest = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i];
            if (sum < lowest) lowest = sum;
            if (sum > highest) highest = sum;
        }

        if ((high - low) > (highest - lowest))
            return (high - low) - (highest - lowest) + 1;
        else
            return 0;
    }

    public static int secretArray2(int[] input, int low, int high) {
        int lowest = 0;
        int highest = 0;
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i];
            lowest = Math.min(sum, lowest);
            highest = Math.max(sum, highest);
        }

        return Math.max((high - low) - (highest - lowest) + 1, 0);
    }

    public static void main(String[] args) {
        int[] input = new int[]{-1,-2,-3};
        int low = 3;
        int high = 10;
        System.out.println(secretArray(input, low, high));
    }
}
