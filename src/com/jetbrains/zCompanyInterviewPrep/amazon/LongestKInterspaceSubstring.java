package com.jetbrains.zCompanyInterviewPrep.amazon;

public class LongestKInterspaceSubstring {
    public static String longestSubstring(String input, int K) {
        if (input == null || input.length() == 0) return "";
        int i = 1;
        while (i < input.length() && Math.abs(input.charAt(i) - input.charAt(i - 1)) <= K) {
            i++;
        }
        String firstSub = input.substring(0, i);
        String secondSub = longestSubstring(input.substring(i), K);

        if (firstSub.length() < secondSub.length()) return secondSub;

        return firstSub;
    }

    public static void main(String[] args) {
        int K = 1;
        String input = "ababbacaabbbb";
        System.out.println(longestSubstring(input, K));
    }
}
