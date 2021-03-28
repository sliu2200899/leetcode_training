package com.jetbrains.classic.advancedDataStructure.dsStack;

public class NextGreaterElements3 {
    public int nextGreaterElement(int n) {
        /*
            similar to teh next permutation...

            should be familiar with that...

         */
        //. 12
        //  21.  21 > 12  valid solution

        // 321
        //
        String str = n + "";
        if (str.length() >= 11) {
            return -1;
        }
        char[] charArr = str.toCharArray();

        // find the pair of two successive numbers, from right to left, that satisfy the condition like a[i] < a[i+1]
        int i = charArr.length - 2;
        while (i >= 0 && charArr[i] >= charArr[i + 1]) {
            i--;
        }

        if (i < 0) {
            return -1;
        }

        if (i >= 0) {
            // find the first element from right to left, that is larger than charArr[i]
            int j = charArr.length - 1;
            while (j >= 0 && charArr[j] <= charArr[i]) {
                --j;
            }
            swap(charArr, i, j);
        }

        reverse(charArr, i+1);

        //
        long res = Long.parseLong(String.valueOf(charArr));
        if ((int)res != res) {
            return -1;
        }

        return (int)res;
    }

    private void swap(char[] arr, int a, int b) {
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private void reverse(char[] arr, int index) {
        int left = index, right = arr.length - 1;
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }
}
