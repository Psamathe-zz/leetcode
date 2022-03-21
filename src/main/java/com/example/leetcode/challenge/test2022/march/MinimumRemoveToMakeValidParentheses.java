package com.example.leetcode.challenge.test2022.march;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 */
public class MinimumRemoveToMakeValidParentheses {
    public static void main(String[] args) {

    }
    public String minRemoveToMakeValid(String s) {
        StringBuilder leftToRight = new StringBuilder();
        char[] chars = s.toCharArray();
        int count = 0;
        for (char c : chars){
            if((c<='z' && c>='a') || c =='(') {
                leftToRight.append(c);
                if(c == '(')
                    count++;
            } else {
                if(count > 0){
                    leftToRight.append(c);
                    count--;
                }
            }
        }
        chars = leftToRight.reverse().toString().toCharArray();
        leftToRight.setLength(0);
        count = 0;
        for (char c : chars){
            if((c<='z' && c>='a') || c ==')') {
                leftToRight.append(c);
                if(c == ')')
                    count++;
            } else {
                if(count > 0){
                    leftToRight.append(c);
                    count--;
                }
            }
        }
        return leftToRight.reverse().toString();
    }
}
