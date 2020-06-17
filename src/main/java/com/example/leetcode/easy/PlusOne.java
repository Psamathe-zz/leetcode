package com.example.leetcode.easy;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Example 2:
 *
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 */
public class PlusOne {
    public static void main(String[] args) {

    }

    public int[] plusOne(int[] digits) {
        Stack<Integer> stack = new Stack<>();
        int value;
        int next = 1;
        for (int i = digits.length - 1; i >= 0 ; i--) {
            value = (digits[i] + next) % 10;
            next = (digits[i] + next) / 10;
            stack.add(value);
        }
        if(next > 0)
            stack.add(next);
        int[] result = new int[stack.size()];
        int index = 0;
        while (!stack.isEmpty()){
            result[index++] = stack.pop();
        }
        return result;
    }
}
