package com.example.leetcode.challenge.test2020.August.week4;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement the StreamChecker class as follows:
 *
 * StreamChecker(words): Constructor, init the data structure with the given words.
 * query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.
 *
 *
 * Example:
 *
 * StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
 * streamChecker.query('a');          // return false
 * streamChecker.query('b');          // return false
 * streamChecker.query('c');          // return false
 * streamChecker.query('d');          // return true, because 'cd' is in the wordlist
 * streamChecker.query('e');          // return false
 * streamChecker.query('f');          // return true, because 'f' is in the wordlist
 * streamChecker.query('g');          // return false
 * streamChecker.query('h');          // return false
 * streamChecker.query('i');          // return false
 * streamChecker.query('j');          // return false
 * streamChecker.query('k');          // return false
 * streamChecker.query('l');          // return true, because 'kl' is in the wordlist
 *
 *
 * Note:
 *
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 2000
 * Words will only consist of lowercase English letters.
 * Queries will only consist of lowercase English letters.
 * The number of queries is at most 40000.
 */
public class StreamChecker {
    public static void main(String[] args) {
        String[] words = new String[]{"cd","f","kl"};
        StreamChecker streamChecker = new StreamChecker(words);
        boolean r = streamChecker.query('a');          // return false
        r = streamChecker.query('b');          // return false
        r = streamChecker.query('c');          // return false
        r = streamChecker.query('d');          // return true, because 'cd' is in the wordlist
        r = streamChecker.query('e');          // return false
        r = streamChecker.query('f');          // return true, because 'f' is in the wordlist
        r = streamChecker.query('g');          // return false
        r = streamChecker.query('h');          // return false
        r = streamChecker.query('i');          // return false
        r = streamChecker.query('j');          // return false
        r = streamChecker.query('k');          // return false
        r = streamChecker.query('l');          // return true, because 'kl' is in the wordlist
        System.out.println(r);
    }


    /**
     * http://www.noteanddata.com/leetcode-1032-Stream-of-Characters-java-solution-note.html
     * http://www.noteanddata.com/leetcode-208-Implement-Trie-Prefix-Tree-java-solution-note.html
     */
    private Node root;
    private List<Character> queryList;

    public StreamChecker(String[] words) {
        root = new Node();
        queryList = new ArrayList<>();

        for(String word: words) {
            char[] arr = word.toCharArray();
            Node cur = root;
            for(int i = arr.length-1; i >= 0; --i) {
                char ch = arr[i];
                int index = (int)(ch-'a');

                if(null == cur.children[index]) {
                    cur.children[index] = new Node();
                }
                cur = cur.children[index];
                if(i == 0) {
                    cur.isWordEnd = true;
                }
            }
        }
    }

    public boolean query(char letter) {
        queryList.add(letter);
        Node cur = root;
        for(int i = queryList.size()-1; i >= 0; --i) {
            int index = (int)(queryList.get(i)-'a');
            if(cur.children[index] == null) {
                return false;
            }
            cur = cur.children[index];
            if(cur.isWordEnd) {
                return true;
            }
        }
        return false;
    }

    static class Node {
        Node[] children;
        boolean isWordEnd;
        public Node() {
            this.children = new Node[26];
            this.isWordEnd = false;
        }
    }
}
