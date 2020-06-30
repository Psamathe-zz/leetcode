package com.example.leetcode.medium;

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
 *
 * Note:
 *
 * S is a balanced parentheses string, containing only ( and ).
 * 2 <= S.length <= 50
 */
public class ScoreOfParentheses {
    public static void main(String[] args) {
        ScoreOfParentheses scoreOfParentheses = new ScoreOfParentheses();
        scoreOfParentheses.scoreOfParentheses("(())");
    }

    public int scoreOfParentheses(String S) {
        Stack<Object> stack = new Stack<>();
        char[] chars = S.toCharArray();
        for (char c : chars){
            if(c == ')'){
                int value = 0;
                if(stack.peek().equals('(')){
                    value = 1;
                    stack.pop();
                    stack.push(value);
                } else {
                    while (!stack.peek().equals('(')){
                        value += Integer.valueOf(stack.pop().toString());
                    }
                    stack.pop();
                    stack.push(value * 2);
                }
            } else {
                stack.push(c);
            }
        }
        int value = 0;
        while (!stack.isEmpty()){
            value += Integer.valueOf(stack.pop().toString());
        }
        return value;
    }

    /**
     * faster solution
     * @param S
     * @return
     */
    public int scoreOfParenthesesV1(String S) {
        Stack<Integer> result = new Stack();
        result.push(0);
        int score = 0;
        for(int i = 0; i < S.length(); i++){
            if(S.charAt(i) == '(') result.push(0);
            else {
                score = result.pop();
                score = (score==0 ? 1 : score*2);
                result.push(result.pop() + score);
            }
        }
        return result.pop();
    }
}
