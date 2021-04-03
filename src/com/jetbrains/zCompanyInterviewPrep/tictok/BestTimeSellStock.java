package com.jetbrains.zCompanyInterviewPrep.tictok;

import java.util.ArrayDeque;
import java.util.Deque;

public class BestTimeSellStock {
    /*
        第一道股票，貌似不是刷题网的题，这个可以手持多支股票。每天可以选择买或者卖或者啥都不做。比如 2 3 4 5 2 4，这个最大利润是8. 第四天和第六天卖，其他时间买。最后居然没做出来。。
     */

    private static int process(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int i = arr.length - 1; i >= 0; --i) {
            if (stack.isEmpty()) {
                stack.push(arr[i]);
            }
            else if (stack.peek() < arr[i]) {
                stack.pop();
                stack.push(arr[i]);
            }
            else if (stack.peek() > arr[i]) {
                res += (stack.peek() - arr[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 2, 10};
        System.out.println(process(arr));
    }
}
