package com.example.leetcode.challenge.test2022.november;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation:
 * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17.
 * Example 2:
 *
 * Input: arr = [11,81,94,43,3]
 * Output: 444
 */
public class SumSubarrayMinimums {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/11273330.html
     * @param A
     * @return
     */
    public int sumSubarrayMins(int[] A) {


        int MOD = (int) Math.pow(10, 9) + 7;
        int len = A.length;
        long sum = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int j;
        int k;
        for (int i = 0; i <= len; i++) {
            int cur = i == len ? Integer.MIN_VALUE : A[i];
            while (!stack.isEmpty() && cur < A[stack.peek()]) {
                j = stack.pop();
                k = stack.isEmpty() ? -1 : stack.peek();
                sum += (long) A[j] * (i - j) * (j - k);
            }
            stack.push(i);
        }
        return (int) (sum % (long) MOD);
    }
}
