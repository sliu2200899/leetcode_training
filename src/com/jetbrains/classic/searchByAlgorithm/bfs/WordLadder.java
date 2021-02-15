package com.jetbrains.classic.searchByAlgorithm.bfs;

import java.util.*;

public class WordLadder {
    /*

        solution 1: using bfs   just working solution
        time: O(len^2 * n)  len is the length of the words and n is the number of the words in input word list
        space: O(len * n)

     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Deque<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        return bfs(queue, beginWord, endWord, wordList, visited);
    }

    private int bfs(Deque<String> queue, String s, String d, List<String> wordList, Set<String> visited) {
        queue.offer(s);
        visited.add(s);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; ++i) {
                String cur = queue.poll();

                if (cur.equals(d)) {
                    return step;
                }

                for (String str : wordList) {
                    if (visited.contains(str)) {
                        continue;
                    }

                    if (isOneLetterDiff(cur, str)) {
                        visited.add(str);
                        queue.offer(str);
                    }
                }
            }
        }
        return 0;
    }

    private boolean isOneLetterDiff(String a, String b) {
        int i = 0;
        if (a.length() != b.length()) return false;

        int diff = 0;
        while (i < a.length()) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
            }
            i++;
        }

        return diff == 1;
    }

    // how to improve the above solution
    /*
        The first intuition for this problem is to build a graph whose nodes represent strings and edges connect strings that are only 1 character apart,
        and then we apply BFS from the startWord node. If we find the endWord, we return the level count of the bfs. This intuition is correct, but there are some places that we can save time.

        several points we can improve
        1. When we build adjacency list graph, we don't use two loops to check every pair of string to see if they are 1 character apart.
            Instead, we make changes to current string to obtain all the strings we can reach from current node, and see if it is in the wordList.
            Thus, there are currentString.length() * 25 case we need to check for every node. This is faster when the wordList set is large,
            since the check-every-pair method need wordList.size() * currentString.length() for each node. Otherwise, your may exceed the running time limit.
        2. For the strings we visited, we remove it from the wordList. This way we don't need to mark visited using another HashSet or something.
        3. Actually, we don't even need to build the adjacency list graph explicitly using a HashMap<String, ArrayList>,
            since we keep all the nodes we can reach in the queue of each level of BFS. This can be seen as the keys of the HashMap are the strings that in the queue,
            and values are the strings that satisfy the 1 character apart in the wordList. Thus, we avoid the time cost of build map for those nodes we don't need to visit.
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {

        HashSet<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord))
            return 0;

        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);
        int depth = 1;

        while(!queue.isEmpty()){
            // System.out.println(queue.toString());
            // System.out.println(depth);
            int size = queue.size();
            for(int i = 0; i < size; i++){
                String curr = queue.poll();

                for(String neig : neighbors(curr, set)){

                    if(neig.equals(endWord)){
                        return depth+1;
                    }
                    queue.offer(neig);
                }
            }

            depth++;
        }

        return 0;
    }

    public List<String> neighbors(String s, HashSet<String> wordList){
        List<String> res = new LinkedList<>();

        for(int i=0;i<s.length();i++){
            char [] chars = s.toCharArray();
            for(char ch = 'a'; ch <= 'z'; ch++){
                chars[i] = ch;
                String word = new String(chars);

                if(word.equals(s))
                    continue;
                if(wordList.remove(word)){
                    //System.out.println(word);
                    res.add(word);
                }
            }
        }
        return res;
    }
}
