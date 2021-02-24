package com.jetbrains.classic.topic.twoPointers;

public class LongPressedName {

    /*
        two pointers
        keep track of one pointer in the while loop, in this case, we can check another pointer's location when it's done.
     */
    public boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0, m = name.length(), n = typed.length();

        while (j < n) {
            if (i < m && name.charAt(i) == typed.charAt(j)) {
                ++i;
                ++j;
            } else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1)) {
                return false;
            } else {
                j++;
            }
        }
        return i == m;
    }
}
