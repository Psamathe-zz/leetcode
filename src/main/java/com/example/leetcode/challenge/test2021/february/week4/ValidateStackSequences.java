package com.example.leetcode.challenge.test2021.february.week4;


import java.util.Stack;

/**
 * Given two sequences pushed and popped with distinct values,
 * return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.
 *
 *
 *
 * Example 1:
 *
 * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * Output: true
 * Explanation: We might do the following sequence:
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * Example 2:
 *
 * Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * Output: false
 * Explanation: 1 cannot be popped before 2.
 *
 *
 * Constraints:
 *
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed is a permutation of popped.
 * pushed and popped have distinct values.
 */
public class ValidateStackSequences {
    public static void main(String[] args) {
        int[] pushed = new int[]{1,2,3,4,5};
        int[] poped = new int[]{4,5,3,2,1};
        ValidateStackSequences validateStackSequences = new ValidateStackSequences();
        boolean res = validateStackSequences.validateStackSequences(pushed,poped);
        System.out.println(res);
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int length1 = pushed.length;
        int length2 = popped.length;
        if(length1 != length2)
            return false;
        int indexPop = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length1; i++) {
            stack.push(pushed[i]);
            while ( !stack.isEmpty() && stack.peek()== popped[indexPop]){
                indexPop++;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
