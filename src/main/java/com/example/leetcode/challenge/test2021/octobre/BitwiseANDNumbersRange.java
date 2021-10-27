package com.example.leetcode.challenge.test2021.octobre;

/**
 * Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.
 *
 *
 *
 * Example 1:
 *
 * Input: left = 5, right = 7
 * Output: 4
 * Example 2:
 *
 * Input: left = 0, right = 0
 * Output: 0
 * Example 3:
 *
 * Input: left = 1, right = 2147483647
 * Output: 0
 */
public class BitwiseANDNumbersRange {
    public static void main(String[] args) {
        BitwiseANDNumbersRange bitwiseANDNumbersRange = new BitwiseANDNumbersRange();
        bitwiseANDNumbersRange.rangeBitwiseAnd(1, 2147483647);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4431646.html
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAnd(int left, int right) {
        int d = Integer.MAX_VALUE;
        while ((left & d) != (right & d)) {
            d <<= 1;
        }
        int res = left & d;
        return res;
    }

    public int rangeBitwiseAndV1(int left, int right) {
        int i = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            ++i;
        }
        return (left << i);
    }
}
