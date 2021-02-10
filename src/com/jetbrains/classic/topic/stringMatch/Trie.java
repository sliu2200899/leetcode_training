package com.jetbrains.classic.topic.stringMatch;

public class Trie {

    private TrieNode root = new TrieNode('/');

    // insert one string into Trie
    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; ++i) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; ++i) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null) {
                return false;
            }
            p = p.children[index];
        }
        if (p.isEndingChar == false) return false;
        else return true;
    }

    public boolean startWith(char[] prefix) {
        TrieNode p = root;
        for (int i  = 0; i < prefix.length; ++i) {
            int index = prefix[i] - 'a';
            if (p.children[index] == null) {
                return false;
            }
            p = p.children[index];
        }

        return true;
    }

    private class TrieNode {
        public char data;  // current char
        public TrieNode[] children = new TrieNode[26];  // TrieNode array for 26 english letters
        public boolean isEndingChar = false;   // boolean isEndingChar

        public TrieNode(char data) {
            this.data = data;
        }
    }
}
