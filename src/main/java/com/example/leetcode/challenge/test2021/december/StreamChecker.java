package com.example.leetcode.challenge.test2021.december;

import java.util.ArrayList;
import java.util.List;

/**
 * Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings words.
 *
 * For example, if words = ["abc", "xyz"] and the stream added the four characters (one by one) 'a', 'x', 'y', and 'z', your algorithm should detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.
 *
 * Implement the StreamChecker class:
 *
 * StreamChecker(String[] words) Initializes the object with the strings array words.
 * boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from the stream forms a word that is in words.
 *
 *
 * Example 1:
 *
 * Input
 * ["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
 * [[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
 * Output
 * [null, false, false, false, true, false, true, false, false, false, false, false, true]
 *
 * Explanation
 * StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
 * streamChecker.query("a"); // return False
 * streamChecker.query("b"); // return False
 * streamChecker.query("c"); // return False
 * streamChecker.query("d"); // return True, because 'cd' is in the wordlist
 * streamChecker.query("e"); // return False
 * streamChecker.query("f"); // return True, because 'f' is in the wordlist
 * streamChecker.query("g"); // return False
 * streamChecker.query("h"); // return False
 * streamChecker.query("i"); // return False
 * streamChecker.query("j"); // return False
 * streamChecker.query("k"); // return False
 * streamChecker.query("l"); // return True, because 'kl' is in the wordlist
 *
 */
public class StreamChecker {

    private Node root;
    private List<Character> queryList;

    /**
     * http://www.noteanddata.com/leetcode-1032-Stream-of-Characters-java-solution-note.html
     * http://www.noteanddata.com/leetcode-208-Implement-Trie-Prefix-Tree-java-solution-note.html
     */
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
