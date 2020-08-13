package com.example.leetcode.challenge.August.week2;


import com.example.leetcode.sometest.A;

import java.util.*;

/**
 * Design an Iterator class, which has:
 *
 * A constructor that takes a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
 * A function next() that returns the next combination of length combinationLength in lexicographical order.
 * A function hasNext() that returns True if and only if there exists a next combination.
 *
 *
 * Example:
 *
 * CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.
 *
 * iterator.next(); // returns "ab"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "ac"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "bc"
 * iterator.hasNext(); // returns false
 *
 *
 * Constraints:
 *
 * 1 <= combinationLength <= characters.length <= 15
 * There will be at most 10^4 function calls per test.
 * It's guaranteed that all calls of the function next are valid.
 */
public class CombinationIterator {
    public static void main(String[] args) {
        String characters = "abc";
        int combinationLength = 2;
        CombinationIterator obj = new CombinationIterator(characters, combinationLength);
        String param_1 = obj.next();
        boolean param_2 = obj.hasNext();
    }

    Queue<String> queue;
    Stack<Character> stack;
    public CombinationIterator(String characters, int combinationLength) {
        queue = new LinkedList<>();
        stack = new Stack<>();
        dfs(characters,combinationLength,0);
    }



    public void dfs(String str,int combinationLength,int index){
        if(stack.size() == combinationLength){
            queue.offer(stack.toString());
        } else if(index == str.length()){
            return;
        }

        stack.push(str.charAt(index));
        dfs(str,combinationLength,index+1);
        stack.pop();
        dfs(str,combinationLength,index+1);
    }

    public String next() {
        return queue.poll();
    }

    public boolean hasNext() {
        return queue.isEmpty();
    }
}
