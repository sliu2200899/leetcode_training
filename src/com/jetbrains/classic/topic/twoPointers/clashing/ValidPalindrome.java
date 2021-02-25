package com.jetbrains.classic.topic.twoPointers.clashing;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        char[] arr = s.toLowerCase().toCharArray();
        int left = 0, right = arr.length-1;

        while (left < right) {
            while(left < right && !Character.isLetterOrDigit(arr[left])) {
                left++;
            }

            while(left < right && !Character.isLetterOrDigit(arr[right])) {
                right--;
            }

            if (left < right && arr[left++] != arr[right--]) {
                return false;
            }
        }

        return true;
    }

    /*
        summary for check alphanumeric characters in java
        1 using Character.isLetterOrDigit(char)
        2 using Apache Commons Lang library

            import org.apache.commons.lang3.StringUtils;

            String s = "ABC12";
		    System.out.println("IsAlphaNumeric: " + StringUtils.isAlphanumeric(s));

            //parameters: the CharSequence to check, may be null
            //returns: true if only contains letters or digits, and is non-null

        3 regular expression
            public static boolean isAlphaNumeric(String s) {
                return s != null && s.matches("^[a-zA-Z0-9]*$");
            }

     */

    /*
        valid palindrom 2
     */
    public boolean validPalindrome2(String s) {

        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return helper(s, left+1, right) ||
                        helper(s, left, right-1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean helper(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
