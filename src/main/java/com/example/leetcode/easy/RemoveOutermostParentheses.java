package com.example.leetcode.easy;

import java.util.Stack;

/**
 * A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
 *
 * A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.
 *
 * Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.
 *
 * Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.
 *
 *
 *
 * Example 1:
 *
 * Input: "(()())(())"
 * Output: "()()()"
 * Explanation:
 * The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
 * After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
 * Example 2:
 *
 * Input: "(()())(())(()(()))"
 * Output: "()()()()(())"
 * Explanation:
 * The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
 * After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
 * Example 3:
 *
 * Input: "()()"
 * Output: ""
 * Explanation:
 * The input string is "()()", with primitive decomposition "()" + "()".
 * After removing outer parentheses of each part, this is "" + "" = "".
 *
 *
 * Note:
 *
 * S.length <= 10000
 * S[i] is "(" or ")"
 * S is a valid parentheses string
 */
public class RemoveOutermostParentheses {
    public static void main(String[] args) {
        String input = "(()())(())(()(()))";
        RemoveOutermostParentheses removeOutermostParentheses = new RemoveOutermostParentheses();
        String result = removeOutermostParentheses.removeOuterParentheses(input);
        System.out.println(result);
    }

    public String removeOuterParentheses(String S) {
        StringBuffer sb = new StringBuffer();
        Stack<String> stack = new Stack<>();
        String[] list = S.split("");
        String  temp = "";
        int left = 0;
        for(String val : list){
            if(val.equals("(")){
                left++;
            } else {
                left--;
            }
            if(left == 0){

                while (stack.size()>1){
                    sb.insert(0,stack.pop());
                }
                temp += sb.toString();
                sb.delete(0, sb.length());
                stack.pop();
            } else {
                stack.push(val);
            }
        }

        return temp;
    }


    /**
     * faster
     * @param S
     * @return
     */
    public String removeOuterParenthesesV2(String S) {
        StringBuilder builder = new StringBuilder();
        int open = 0;

        for (char c: S.toCharArray()) {
            if (c == '(') {
                if (open++ > 0) {
                    builder.append(c);
                }
            } else { // c == ')'
                if (--open > 0) {
                    builder.append(c);
                }
            }
        }

        return builder.toString();
    }

    /**
     * less memory
     * @param
     * @return
     */
    public String removeOuterParenthesesV3(String S) {
        char [] chars = S.toCharArray();
        Stack<Integer> stack = new Stack<Integer>();

        for (int i=0; i<S.length(); i++) {
            if (S.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.size() == 1) {
                    chars[i] = ' ';
                    chars[stack.peek()] = ' ';
                }
                stack.pop();
            }
        }
        StringBuilder builder = new StringBuilder();

        for (int i=0;i<chars.length;i++) {
            if (chars[i] !=' ') {
                builder.append(chars[i]);
            }
        }
        return builder.toString();
    }
}
