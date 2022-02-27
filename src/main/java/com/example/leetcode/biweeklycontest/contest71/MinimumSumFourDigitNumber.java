package com.example.leetcode.biweeklycontest.contest71;


import java.util.Arrays;

/**
 * You are given a positive integer num consisting of exactly four digits. Split num into two new integers new1 and new2 by using the digits found in num. Leading zeros are allowed in new1 and new2, and all the digits found in num must be used.
 *
 * For example, given num = 2932, you have the following digits: two 2's, one 9 and one 3. Some of the possible pairs [new1, new2] are [22, 93], [23, 92], [223, 9] and [2, 329].
 * Return the minimum possible sum of new1 and new2.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 2932
 * Output: 52
 * Explanation: Some possible pairs [new1, new2] are [29, 23], [223, 9], etc.
 * The minimum sum can be obtained by the pair [29, 23]: 29 + 23 = 52.
 * Example 2:
 *
 * Input: num = 4009
 * Output: 13
 * Explanation: Some possible pairs [new1, new2] are [0, 49], [490, 0], etc.
 * The minimum sum can be obtained by the pair [4, 9]: 4 + 9 = 13.
 */
public class MinimumSumFourDigitNumber {
    public static void main(String[] args) {

    }

    public int minimumSum(int num) {
        int[] v = new int[4];
        int index = 0;
        while (num != 0) {
            v[index++] = num % 10;
            num /= 10;
        }
        Arrays.sort(v);

        int a = v[0] * 10 + v[3];
        int b = v[1] * 10 + v[2];

        return a + b;
    }
}
