package com.example.leetcode.challenge.test2020.may.week2;

/**
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 *
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 */
public class ImplementTrie {
    public static void main(String[] args) {
        String word = "apple";
        String prefix = "app";
        ImplementTrie obj = new ImplementTrie();
        obj.insert(word);
        boolean param_2 = obj.search(word);
        boolean param_3 = obj.startsWith(prefix);
    }

    /**
     * Initialize your data structure here.
     */
    TrieNode root;
    public ImplementTrie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode p = root;
        for (Character a : word.toCharArray()) {
            int i = a - 'a';
            if (p.listNode[i] == null)
                p.listNode[i] = new TrieNode();
            p = p.listNode[i];
        }
        p.isWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode p = root;
        for (Character a : word.toCharArray()) {
            int i = a - 'a';
            if (p.listNode[i] == null)
                return false;
            p = p.listNode[i];
        }
        return p.isWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode p = root;
        for (Character a : prefix.toCharArray()) {
            int i = a - 'a';
            if (p.listNode[i] == null)
                return false;
            p = p.listNode[i];
        }
        return true;
    }

    class TrieNode {
        TrieNode[] listNode;
        boolean isWord;
        TrieNode() {
            listNode = new TrieNode[26];
            isWord = false;
        }
    }
}

