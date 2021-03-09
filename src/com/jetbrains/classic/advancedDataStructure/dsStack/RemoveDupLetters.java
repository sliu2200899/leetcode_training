package com.jetbrains.classic.advancedDataStructure.dsStack;

import java.util.*;

public class RemoveDupLetters {
    /*
        brute force: TLE

     */
    public String removeDuplicateLetters(String s) {
        // priority queue
        // get all

        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        List<String> list = new ArrayList<>();
        dfs(s, list, 0, "", map);

//        list.forEach(System.out::println);

        PriorityQueue<String> pqmax = new PriorityQueue<>(Collections.reverseOrder());

        for (String str : list) {
            pqmax.offer(str);
            if (pqmax.size() > 1) {
                pqmax.poll();
            }
        }
        return pqmax.poll();
    }

    private void dfs(String s, List<String> list, int index, String tmp, Map<Character, Integer> map) {
        if (index == s.length()) {
            list.add(tmp);
            return;
        }

        char c = s.charAt(index);
        int num = map.get(c);
        if (num > 1) {
            // skip cur char
            map.put(c, num - 1);
            dfs(s, list, index + 1, tmp, map);
            map.put(c, num);

            // add the cur char
            map.put(c, 0);
            dfs(s, list, index + 1, tmp + c, map);
            map.put(c, num);
        } else if (num == 1) {
            map.put(c, 0);
            dfs(s, list, index + 1, tmp + c, map);
            map.put(c, 1);
        } else {
            dfs(s, list, index + 1, tmp, map);
        }
    }

    /*
        Greedy:  Solving letter by letter
        We use idea number one from the intuition. In each iteration, we determine leftmost letter in our solution.
        This will be the smallest character such that its suffix contains at least one copy of every character in the string.
        We determine the rest our answer by recursively calling the function on the suffix we generate from the original string (leftmost letter is removed).
     */
    public String removeDuplicateLetters1(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            cnt[s.charAt(i) - 'a']++;
        }
        // we create a counter and end the iteration once the suffix doesn't have each unique character
        // get the pos which is the index of the smallest character we encounter before the iteration ends
        int pos = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            if (--cnt[s.charAt(i) - 'a'] == 0) break;
        }

        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters1(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }


    /*
        Greedy: Solving with Stack
        We use idea number two from the intuition. We will keep a stack to store the solution we have built as we iterate over the string,
        and we will delete characters off the stack whenever it is possible and it makes our string smaller.

        Each iteration we add the current character to the solution if it hasn't already been used. We try to remove as many characters
        as possible off the top of the stack, and then add the current character

        The conditions for deletion are:

        The character is greater than the current characters
        The character can be removed because it occurs later on
        At each stage in our iteration through the string, we greedily keep what's on the stack as small as possible.
     */
    public String removeDuplicateLetters2(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            map.put(s.charAt(i), i); // store the character's last occurance index
        }

        Set<Character> seen = new HashSet<>();

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (!seen.contains(c)) {
                // if the last letter in our solution
                //  1. exists
                //  2. is greater than c so removing it will make teh string smaller
                //. 3. it's not the last occurance
                // we remove it from the solution to keep the solution optimal

                while (!stack.isEmpty() && c < stack.peek() &&
                        map.get(stack.peek()) > i) {
                    seen.remove(stack.pop());
                }

                seen.add(c);
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : stack) sb.append(c);
        return sb.reverse().toString();
    }
}
