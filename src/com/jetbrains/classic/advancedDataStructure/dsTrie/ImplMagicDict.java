package com.jetbrains.classic.advancedDataStructure.dsTrie;

public class ImplMagicDict {
    private class TrieNode{
        String word;
        TrieNode[] children;
        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }
    private TrieNode root;
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
    /** Initialize your data structure here. */
    public void MagicDictionary() {
        this.root  = new TrieNode();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            insert(word);
        }
    }

    public boolean search(String searchWord) {
        /*
            time: O(N^2) N is the length of the given input
         */
        char[] charArr = searchWord.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < charArr.length; ++i) {
            for (char c = 'a'; c <= 'z'; ++c) {
                if (charArr[i] == c) continue;

                char tmp = charArr[i];
                charArr[i] = c;
                if (contains(charArr, p, i)) return true;
                charArr[i] = tmp;
            }

            p = p.children[charArr[i] - 'a'];
            if (p == null) return false;
        }
        return false;
    }
    /*
        here our contains() will bring the TrieNode and index because we don't need to explore the word from scratch every time.
     */
    private boolean contains(char[] word, TrieNode prefix, int s)  {
        TrieNode p = prefix;
        for (int i = s; i < word.length; ++i) {
            int index = word[i] - 'a';
            if (p.children[index] == null) {
                return false;
            }
            p = p.children[index];
        }
        return p.word != null;
    }
}
