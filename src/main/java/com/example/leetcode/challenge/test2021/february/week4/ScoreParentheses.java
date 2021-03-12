package com.example.leetcode.challenge.test2021.february.week4;


import java.util.Stack;

/**
 * Given a balanced parentheses string S, compute the score of the string based on the following rule:
 *
 * () has score 1
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 *
 *
 * Example 1:
 *
 * Input: "()"
 * Output: 1
 * Example 2:
 *
 * Input: "(())"
 * Output: 2
 * Example 3:
 *
 * Input: "()()"
 * Output: 2
 * Example 4:
 *
 * Input: "(()(()))"
 * Output: 6
 *
 */
public class ScoreParentheses {
    public static void main(String[] args) {
        ScoreParentheses scoreParentheses = new ScoreParentheses();
        int res = scoreParentheses.scoreOfParentheses("(())");
        System.out.println(res);
    }

    public int scoreOfParentheses(String S) {
        char[] chars = S.toCharArray();
        int cur;
        Stack<Object> stack = new Stack<>();
        for (char c: chars){
            if(c == '('){
                stack.push(c);
            } else {
                cur = 0;
                while (stack.peek() instanceof Integer){
                    cur += (Integer) stack.pop();
                }
                stack.pop();
                cur = cur == 0 ? 1:cur*2;
                stack.push(cur);
            }
        }
        int res = 0;
        while (!stack.isEmpty()){
            res += (Integer) stack.pop();
        }
        return res;
    }

    public int scoreOfParenthesesV0(String S) {

        Stack<Integer> results = new Stack<>();
        int out = 0;

        for (char c : S.toCharArray()){
            if (c == '('){
                results.push(out);
                out = 0;
            }
            else{
                out = results.pop() + Math.max(out * 2, 1);
            }
        }
        return out;
    }
}
