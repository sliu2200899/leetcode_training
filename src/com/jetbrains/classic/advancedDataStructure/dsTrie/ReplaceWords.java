package com.jetbrains.classic.advancedDataStructure.dsTrie;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReplaceWords {
    /*
        time: O(N) N is the length of the sentence. every query of a word is in linear time
        space: O(N). the size of our trie.
     */
    private class TrieNode{
        String word;
        TrieNode[] children;
        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }
    private TrieNode root = new TrieNode();
    private void insert(String word) {
        char[] charArr = word.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < charArr.length; ++i) {
            int index = charArr[i] - 'a';
            if (p.children[index] == null) {
                p.children[index] = new TrieNode();
            }
            p = p.children[index];
        }
        p.word = word;
    }
    private String search(String word) {
        char[] charArr = word.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < charArr.length; ++i) {
            int index = charArr[i] - 'a';
            if (p.children[index] == null || p.word != null) {
                return p.word;
            }
            else {
                p = p.children[index];
            }
        }
        return p.word;
    }
    public String replaceWords(List<String> dictionary, String sentence) {
        // step 1: build trie based on the dictionary
        for (String s: dictionary) {
            insert(s);
        }

        // step 2: iterate the sentence and replace the successor with the root if it is possible
        String[] arr = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; ++i) {
            String res = search(arr[i]);
            if (res != null) {
                arr[i] = res;
            }
            sb.append(arr[i] + " ");
        }
        return sb.toString().trim();
    }

    /*
        another solution is use hashtable
        time: O(sum of wi ^ 2)  where wi is the length of the ith word. we might check every prefix, the ith of which is O(w^2) work.
        space: O(N)
     */
    public String replaceWords2(List<String> dict, String sentence) {
        if (dict == null || dict.size() == 0) return sentence;

        Set<String> set = new HashSet<>();
        for (String s : dict) set.add(s);

        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split("\\s+");

        for (String word : words) {
            String prefix = "";
            for (int i = 1; i <= word.length(); ++i) {
                prefix = word.substring(0, i);
                if (set.contains(prefix)) break;
            }
            sb.append(" " + prefix);
        }

        return sb.toString().trim();
    }
}
