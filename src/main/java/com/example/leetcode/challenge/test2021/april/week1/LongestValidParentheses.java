package com.example.leetcode.challenge.test2021.april.week1;


import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 3 * 104
 * s[i] is '(', or ')'.
 * "()(())""()(()"
 */
public class LongestValidParentheses {
    public static void main(String[] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        longestValidParentheses.longestValidParentheses("()(()");
    }

    /**
     * faster solution
     * @param s
     * @return
     */
    public static int longestValidParenthesesV0(String s) {
        int max = 0;
        int thisMax = 0;
        Stack<Integer> stack = new Stack<>();
        int [] mem = new int[s.length()];
        for(int idx=0; idx < s.length(); ++idx) {
            char ch = s.charAt(idx);
            if(ch == ')') {
                if(stack.isEmpty()) {
                    mem[idx] = -1;
                    continue;
                }
                //~ matched
                mem[stack.pop()] = 2;
            } else {
                mem[idx] = -1;
                stack.push(idx);
            }
        }
        // retrieve
        for(int idx=0; idx < mem.length; ++idx) {
            if(mem[idx] == -1) {
                thisMax = 0;
            } else {
                thisMax += mem[idx];
            }
            if(thisMax > max) max = thisMax;
        }
        return max;
    }

    public int longestValidParentheses(String s) {
        int res = 0;
        Stack<ValidCharacter> stack = new Stack<>();
        char[] chars = s.toCharArray();
        int length = chars.length;
        boolean[] valid = new boolean[length];
        char c;
        for (int i = 0; i < length; i++) {
            c = chars[i];
            if(!stack.isEmpty() && stack.peek().c == '(' && c == ')'){
                valid[stack.pop().index] = true;
                valid[i] = true;
            } else  {
                stack.push(new ValidCharacter(c,i));
            }
        }

        int temp = 0;
        for (int i = 0; i < length; i++) {
            if(valid[i])
                temp++;
            else{
                res = Math.max(res,temp);
                temp = 0;
            }
        }
        res = Math.max(res, temp);
        return res;
    }

    public class ValidCharacter{
        Character c;
        Integer index;

        public ValidCharacter(Character c, Integer index) {
            this.c = c;
            this.index = index;
        }
    }
}
