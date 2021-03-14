package com.jetbrains.classic.advancedDataStructure.dsTrie;

public class implTrie {
    private class TrieNode{
        public String word;
        public TrieNode[] children;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }
    private TrieNode root;
    /** Initialize your data structure here. */
    public void Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
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

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] charArr = word.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < charArr.length; ++i) {
            int index = charArr[i] - 'a';
            if (p.children[index] == null) {
                return false;
            }
            p = p.children[index];
        }
        if (p.word == null) return false;
        else return true;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] charArr = prefix.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < charArr.length; ++i) {
            int index = charArr[i] - 'a';
            if (p.children[index] == null) {
                return false;
            }
            p = p.children[index];
        }
        return true;
    }
}
