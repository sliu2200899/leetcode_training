package com.jetbrains.classic.topic.stringMatch.conversion;

public class CompareVersion {
    public int compareVersion(String version1, String version2) {
        String[] v1Arr = version1.split("\\.");
        String[] v2Arr = version2.split("\\.");
        int index = 0;
        while (index < v1Arr.length || index < v2Arr.length) {
            int num1 = (index >= v1Arr.length ? 0 : getNumbersPerRevision(v1Arr[index]));
            int num2 = (index >= v2Arr.length ? 0 : getNumbersPerRevision(v2Arr[index]));

            if (num1 > num2) {
                return 1;
            } else if (num1 < num2) {
                return -1;
            }

            index++;
        }

        return 0;
    }

    private int getNumbersPerRevision(String s) {
        int num = 0, i = 0;
        while (i < s.length()) {
            num = num * 10 + (s.charAt(i) - '0');
            i++;
        }
        return num;
    }
}
