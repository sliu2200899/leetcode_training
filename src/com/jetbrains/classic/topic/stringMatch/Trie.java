package com.jetbrains.classic.topic.stringMatch;

public class Trie {

    private TrieNode root = new TrieNode('/');

    // insert one string into Trie
    public void insert(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); ++i) {
            int index = s.charAt(i) - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(s.charAt(i));
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isTerminated = true;
        p.word = s;
    }

    public boolean find(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); ++i) {
            int index = s.charAt(i) - 'a';
            if (p.children[index] == null) {
                return false;
            }
            p = p.children[index];
        }
        return p.isTerminated;
    }

    public boolean startWith(String prefix) {
        TrieNode p = root;
        for (int i  = 0; i < prefix.length(); ++i) {
            int index = prefix.charAt(i) - 'a';
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
        public boolean isTerminated = false;   // boolean isEndingChar
        public String word = null;

        public TrieNode(char data) {
            this.data = data;
        }
    }
}
