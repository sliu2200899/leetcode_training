package com.jetbrains.classic.sorting;

import java.util.Arrays;



/*

For simplicity, consider the data in the range 0 to 9.
Input data: 1, 4, 1, 2, 7, 5, 2
  1) Take a count array to store the count of each unique object.
  Index:     0  1  2  3  4  5  6  7  8  9
  Count:     0  2  2  0   1  1  0  1  0  0

  2) Modify the count array such that each element at each index
  stores the sum of previous counts.
  Index:     0  1  2  3  4  5  6  7  8  9
  Count:     0  2  4  4  5  6  6  7  7  7

    The modified count array indicates the position of each object in
    the output sequence.

  3) Output each object from the input sequence followed by
  decreasing its count by 1.
  Process the input data: 1, 4, 1, 2, 7, 5, 2. Position of 1 is 2.
  Put data 1 at index 2 in output. Decrease count by 1 to place
  next data 1 at an index 1 smaller than this index.

  0, 1, 2, 3, 4, 5, 6
  1, 1, 2, 2, 4, 5, 7

 */

// 4, 5, 4, 9, 4, 5
public class CountingSort {
    public static void sort(int arr[], int k) {
        if (arr == null || arr.length == 0) return;

        int len = arr.length;

        // optional:  if we don't know the k
        // 查找数组中数据的范围
//        int max = arr[0];
//        for (int i = 1; i < arr.length; ++i) {
//            if (max < arr[i]) {
//                max = arr[i];
//            }
//        }

        int[] countArr = new int[k + 1];  // length is the range of the input array
        Arrays.fill(countArr, 0); // initialize count array as 0

        // store the number of times of appearance
        for (int num : arr) {
            countArr[num]++;
        }

        // make summation to know how many elements are less than or equal to a particular number.
        for (int i = 1; i < countArr.length; ++i) {
            countArr[i] += countArr[i - 1];
        }

        // output array that will have sorted arr
        int[] output = new int[len];
        for (int i = len - 1; i >= 0; --i) {
            int index = countArr[arr[i]] - 1;
            output[index] = arr[i];
            --countArr[arr[i]];
        }

        // copy the output array to arr, so that arr now contains sorted integers
        for (int i = 0; i < len; ++i) {
            arr[i] = output[i];
        }
    }
}
