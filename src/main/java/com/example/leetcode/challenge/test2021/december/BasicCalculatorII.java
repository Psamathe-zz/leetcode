package com.example.leetcode.challenge.test2021.december;

import java.util.Stack;

/**
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: s = " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: s = " 3+5 / 2 "
 * Output: 5
 */
public class BasicCalculatorII {
    public static void main(String[] args) {

    }

    public int calculate(String s) {
        long res = 0, num = 0, n = s.length();
        char op = '+';
        Stack<Long> st = new Stack<>();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) >= '0') {
                num = num * 10 + s.charAt(i) - '0';
            }
            if ((s.charAt(i) < '0' && s.charAt(i) != ' ') || i == n - 1) {
                if (op == '+')
                    st.add(num);
                if (op == '-')
                    st.add(-num);
                if (op == '*' || op == '/') {
                    long val = st.pop();
                    long tmp = (op == '*') ? val * num : val / num;
                    st.push(tmp);
                }
                op = s.charAt(i);
                num = 0;
            }
        }
        while (!st.empty()) {
            res += st.pop();
        }
        return (int)res;
    }
}
