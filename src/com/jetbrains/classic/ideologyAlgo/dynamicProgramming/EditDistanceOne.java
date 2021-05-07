package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class EditDistanceOne {

    /*
        diff between one edit distance and edit distance is that here we just check two string has only one edit distance.

        some key points we should learn
            1. always check the smaller string
            2. how to deal with abcd == abcde problem, check it at the end of program.
     */
    public boolean isOneEditDistance(String s, String t) {
        int ns = s.length();
        int nt = t.length();

        // ensure that s is shorter than t
        if (ns > nt) {
            return isOneEditDistance(t, s);
        }

        // the strings are not one edit away distance if the length diff is more than 1
        if (nt - ns > 1) {
            return false;
        }

        for (int i = 0; i < ns; ++i) {
            if (s.charAt(i) != t.charAt(i)) {
                /*
                    If there is no differences between the first len(s) characters, only two situations are possible :
                    1. the strings are equals
                    2. the strings are one edit away distance

                */
                if (ns == nt) {
                    // if strings have the same length
                    return s.substring(i+1).equals(t.substring(i+1));
                }
                else {
                    // if strings have different lengths
                    return s.substring(i).equals(t.substring(i+1));
                }
            }
        }

        // if there is no diffs on ns distance
        // the strings are one edit away only if t has one more character
        return (ns + 1 == nt);
    }
}
