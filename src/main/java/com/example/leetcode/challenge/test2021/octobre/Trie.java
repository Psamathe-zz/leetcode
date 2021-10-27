package com.example.leetcode.challenge.test2021.octobre;

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 *
 * Example 1:
 *
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 *
 */
public class Trie {
    public static void main(String[] args) {
        String word = "apple";
        String prefix = "app";
        Trie obj = new Trie();
        obj.insert(word);
        boolean param_2 = obj.search(word);
        boolean param_3 = obj.startsWith(prefix);
    }

    /**
     * Initialize your data structure here.
     */
    TrieNode root;
    public Trie() {
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
