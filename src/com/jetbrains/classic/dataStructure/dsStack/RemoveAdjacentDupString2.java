package com.jetbrains.classic.dataStructure.dsStack;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAdjacentDupString2 {
    /*
        stack with Reconstruction
        If we store both the count and the character in the stack, we do not have to modify the string. Instead, we can reconstruct the result from characters and counts in the stack.

        Algorithm

        Iterate through the string:

        If the current character is the same as on the top of the stack, increment the count.

        Otherwise, push 1 and the current character to the stack.
        If the count on the top of the stack equals k, pop from the stack.

        Build the result string using characters and counts in the stack.

        time: O(n)  where nn is a string length. We process each character in the string once.
        space: O(n)
     */
    public String removeDuplicates(String s, int k) {
        Deque<Cell> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            if (stack.isEmpty() || s.charAt(i) != stack.peek().c) {
                stack.push(new Cell(s.charAt(i), 1));
            } else {
                int num = stack.peek().level;
                if (num != k - 1) {
                    stack.push(new Cell(s.charAt(i), stack.peek().level + 1));
                }
                else {
                    while (!stack.isEmpty() && stack.peek().c == s.charAt(i)) {
                        stack.pop();
                    }
                }
            }
        }
        StringBuilder sb =new StringBuilder();
        for (Cell cell: stack) sb.append(cell.c);
        return sb.reverse().toString();
    }

    private class Cell{
        public char c;
        public int level;

        public Cell(char c, int level) {
            this.c = c;
            this.level = level;
        }
    }


    /*
        using two pointer
        is different from the above one
        only use stack to store unique element's count
        [1,2,]
     */
    public String removeDuplicates2(String s, int k) {
        /*
        Algorithm

        Initialize the slow pointer (j) with zero.

        Move the fast pointer (i) through the string:

        Copy s[i] into s[j].

        If s[j] is the same as s[j - 1], increment the count on the top of the stack.

        Otherwise, push 1 to the stack.
        If the count equals k, decrease j by k and pop from the stack.

        Return j first characters of the string.
        */
        Deque<Integer> stack = new ArrayDeque<>();
        char[] arr = s.toCharArray();
        int j = 0;  // slow pointer track the result string
        for (int i = 0; i < s.length(); ++i, ++j) {
            arr[j] = arr[i];

            if (j == 0 || arr[j] != arr[j - 1]) {
                stack.push(1);
            }
            else {
                int num = stack.pop() + 1;
                if (num == k) {
                    j = j - k;
                } else {
                    stack.push(num);
                }
            }

        }
        return new String(arr, 0, j);
    }
}
