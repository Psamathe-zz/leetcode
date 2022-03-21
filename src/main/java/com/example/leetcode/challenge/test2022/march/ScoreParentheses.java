package com.example.leetcode.challenge.test2022.march;

import java.util.Stack;

/**
 * Given a balanced parentheses string s, return the score of the string.
 *
 * The score of a balanced parentheses string is based on the following rule:
 *
 * "()" has score 1.
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 *
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: 1
 * Example 2:
 *
 * Input: s = "(())"
 * Output: 2
 * Example 3:
 *
 * Input: s = "()()"
 * Output: 2
 */
public class ScoreParentheses {
    public static void main(String[] args) {
        ScoreParentheses scoreParentheses = new ScoreParentheses();
        scoreParentheses.scoreOfParentheses("(())");
    }

    public int scoreOfParentheses(String s) {
        Stack<Object> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if(c == '(') {
                stack.push(c);
            } else {
                Integer val = 0;
                while (!stack.peek().equals('(')) {
                    val += Integer.parseInt(stack.pop().toString());
                }
                stack.pop();
                if(val == 0) {
                    stack.push(1);
                } else
                    stack.push(val * 2);
            }
        }
        int value = 0;
        while (!stack.isEmpty()){
            value += Integer.valueOf(stack.pop().toString());
        }
        return value;
    }
}
