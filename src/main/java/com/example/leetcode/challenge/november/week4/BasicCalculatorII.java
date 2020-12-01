package com.example.leetcode.challenge.november.week4;


import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: " 3+5 / 2 "
 * Output: 5
 * Note:
 *
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class BasicCalculatorII {
    public static void main(String[] args) {
        BasicCalculatorII basicCalculatorII = new BasicCalculatorII();
        basicCalculatorII.calculate("3+2*2");
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
