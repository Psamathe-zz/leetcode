package com.example.leetcode.challenge.test2021.november;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Design the CombinationIterator class:
 *
 * CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
 * next() Returns the next combination of length combinationLength in lexicographical order.
 * hasNext() Returns true if and only if there exists a next combination.
 *
 *
 * Example 1:
 *
 * Input
 * ["CombinationIterator", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [["abc", 2], [], [], [], [], [], []]
 * Output
 * [null, "ab", true, "ac", true, "bc", false]
 *
 * Explanation
 * CombinationIterator itr = new CombinationIterator("abc", 2);
 * itr.next();    // return "ab"
 * itr.hasNext(); // return True
 * itr.next();    // return "ac"
 * itr.hasNext(); // return True
 * itr.next();    // return "bc"
 * itr.hasNext(); // return False
 *
 */
public class CombinationIterator {
    public static void main(String[] args) {
        CombinationIterator combinationIterator = new CombinationIterator("abc", 2);
        System.out.println(combinationIterator.next());
        System.out.println(combinationIterator.hasNext());
        System.out.println(combinationIterator.next());
        System.out.println(combinationIterator.hasNext());
        System.out.println(combinationIterator.next());
        System.out.println(combinationIterator.hasNext());
    }

    Queue<String> queue;
    public CombinationIterator(String characters, int combinationLength) {
        queue = new LinkedList<>();
        dfs(0, combinationLength, new StringBuilder(), characters);
    }

    private void dfs(int start, int target, StringBuilder sb, String s) {
        if(sb.length() == target) {
            queue.add(sb.toString());
            return;
        }
        for(int i = start; i < s.length(); i++) {
            dfs(i + 1, target, sb.append(s.charAt(i)), s);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public String next() {
        return queue.poll();
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

}
