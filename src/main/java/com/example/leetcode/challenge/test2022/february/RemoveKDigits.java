package com.example.leetcode.challenge.test2022.february;

import java.util.Stack;

/**
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 *
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 *
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
public class RemoveKDigits {
    public static void main(String[] args) {

    }

    public String removeKdigits(String num, int k) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            int value = Integer.valueOf(num.substring(i, i + 1));
            while (!stack.isEmpty() && value < stack.peek() && k > 0){
                stack.pop();
                k--;
            }
            if (!stack.isEmpty() || value != 0)
                stack.push(value);
        }
        while (!stack.isEmpty() && k > 0){
            stack.pop();
            k--;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (!stack.isEmpty()){
            stringBuffer.append(stack.pop());
        }

        return stringBuffer.length()==0?"0":stringBuffer.reverse().toString();
    }
}
