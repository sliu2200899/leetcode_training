package com.jetbrains.classic.advancedDataStructure.dsStack;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {
    /*
    clarify:
        1.input, output, example
        2. rules
            a. '.' refers the curr dir
            b. '..' refers to the up level
            c. '//' =-> '/'
            d. '...'  => file or dir names

            stack to store the path we encountered.
                home. =>. push it into stack
                . => ... push it into stack
                / =>
                    check top of the stack
                    ..
                    ..

            output from stack...

            the key here is how to output
                if you want to output the elements in stack from bottom to top,
                you can just use stack.pollLast()   since Deque is two ended linked list
*/
    public String simplifyPath(String path) {

        String[] arr = path.split("/");

        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i].isEmpty() || arr[i].equals(".")) {
                continue;
            }
            else if (arr[i].equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(arr[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append("/");
            sb.append(stack.pollLast());
        }

        return sb.length() == 0 ? "/" : sb.toString();
    }
}
