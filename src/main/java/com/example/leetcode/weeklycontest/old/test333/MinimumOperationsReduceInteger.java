package com.example.leetcode.weeklycontest.old.test333;

/**
 * You are given a positive integer n, you can do the following operation any number of times:
 *
 * Add or subtract a power of 2 from n.
 * Return the minimum number of operations to make n equal to 0.
 *
 * A number x is power of 2 if x == 2i where i >= 0.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 39
 * Output: 3
 * Explanation: We can do the following operations:
 * - Add 20 = 1 to n, so now n = 40.
 * - Subtract 23 = 8 from n, so now n = 32.
 * - Subtract 25 = 32 from n, so now n = 0.
 * It can be shown that 3 is the minimum number of operations we need to make n equal to 0.
 * Example 2:
 *
 * Input: n = 54
 * Output: 3
 * Explanation: We can do the following operations:
 * - Add 21 = 2 to n, so now n = 56.
 * - Add 23 = 8 to n, so now n = 64.
 * - Subtract 26 = 64 from n, so now n = 0.
 * So the minimum number of operations is 3.
 */
public class MinimumOperationsReduceInteger {
    public static void main(String[] args) {
        MinimumOperationsReduceInteger minimumOperationsReduceInteger = new MinimumOperationsReduceInteger();
        minimumOperationsReduceInteger.minOperations(39);
    }

    public int minOperations(int n) {
        int next;
        int res = 0;
        while (n != 0) {
            next = findNext(n);
            n = Math.abs(n - next);
            res++;
        }
        return res;
    }

    public int findNext(int n) {
        int val = 1;
        while (val * 2 < n) {
            val *= 2;
        }
        if(Math.abs(val - n) <= Math.abs(val * 2 - n)) {
            return val;
        } else {
            return val*2;
        }
    }
}
