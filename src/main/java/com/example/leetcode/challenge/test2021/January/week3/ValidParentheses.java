package com.example.leetcode.challenge.test2021.January.week3;

import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 *
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: true
 * Example 2:
 *
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: s = "(]"
 * Output: false
 * Example 4:
 *
 * Input: s = "([)]"
 * Output: false
 * Example 5:
 *
 * Input: s = "{[]}"
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 */
public class ValidParentheses {
    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        boolean res = validParentheses.isValid("()[]{}");
        System.out.println(res);
    }

    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        char temp;
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if(c == '(' || c == '{' || c == '[' ) {
                stack.push(c);
            } else {
                if(stack.isEmpty() )
                    return false;
                temp = stack.pop();
                if((temp == '(' && c != ')' ) ||
                        (temp == '{' && c != '}' ) ||
                        (temp == '[' && c != ']' )){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
