package com.jetbrains.mj;

public class PalindromSubstring1 {
    /*
    You are given a string s. Consider the following algorithm applied to this string:

Take all the prefixes of the string, and choose the longest palindrome between them.
If this chosen prefix contains at least two characters, cut this prefix from s and go back to the first step with the updated string. Otherwise, end the algorithm with the current string s as a result.
Your task is to implement the above algorithm and return its result when applied to string s.

Note: you can click on the prefixes and palindrome words to see the definition of the terms if you're not familiar with them.

Example

For s = "aaacodedoc", the output should be palindromeCutting(s) = "".

The initial string s = "aaacodedoc" contains only three prefixes which are also palindromes - "a", "aa", "aaa". The longest one between them is "aaa", so we cut if from s.
Now we have string "codedoc". It contains two prefixes which are also palindromes - "c" and "codedoc". The longest one between them is "codedoc", so we cut if from the current string and obtain the empty string.
Finally the algorithm ends on the empty string, so the answer is "".
For s = "codesignal", the output should be palindromeCutting(s) = "codesignal".
The initial string s = "codesignal" contains the only prefix, which is also palindrome - "c". This prefix is the longest, but doesn't contain two characters, so the algorithm ends with string "codesignal" as a result.

For s = "", the output should be palindromeCutting(s) = "".
     */

    public String process(String s) {

        if (s == null || s.length() == 0) return "";

        int start = 0;
        String tmp = s.substring(start);
        while (tmp.length() != 0) {
            int len = longestLenPalindrome(tmp);
            if (len == 1) {
                return tmp;
            }

            tmp = tmp.substring(len);
        }
        return "";
    }

    private int longestLenPalindrome(String s) {
        int end = 1;
        for (int i = 1; i <= s.length(); ++i) {
            if (isPalindrome(s.substring(0, i))) {
                end = Math.max(end, i);
            }
        }
        return end;
    }

    private boolean isPalindrome(String s) {
        int start = 0, end = s.length() - 1;
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
