package com.example.leetcode.challenge.test2022.march;

/**
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [0,1,1]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * Example 2:
 *
 * Input: n = 5
 * Output: [0,1,1,2,1,2]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 */
public class CountingBits {
    public static void main(String[] args) {

    }

    public int[] countBits(int n) {
        if (n < 0)
            return new int[1];

        // allocate array incuding 0->num
        int[] countBitArray = new int[n + 1];

        // initial DP data
        countBitArray[0] = 0;

        for (int i = 1; i <= n; i++) {
            // if num is even, bit count is same as num / 2
            // if odd, bit count is same as (num / 2) + 1
            countBitArray[i] = countBitArray[i >> 1] + i % 2;
        }

        return countBitArray;
    }
}
