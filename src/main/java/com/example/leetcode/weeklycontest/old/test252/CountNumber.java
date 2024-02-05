package com.example.leetcode.weeklycontest.old.test252;

/**
 * A sequence is special if it consists of a positive number of 0s, followed by a positive number of 1s, then a positive number of 2s.
 *
 * For example, [0,1,2] and [0,0,1,1,1,2] are special.
 * In contrast, [2,1,0], [1], and [0,1,2,0] are not special.
 * Given an array nums (consisting of only integers 0, 1, and 2), return the number of different subsequences that are special. Since the answer may be very large, return it modulo 109 + 7.
 *
 * A subsequence of an array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements. Two subsequences are different if the set of indices chosen are different.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,2,2]
 * Output: 3
 * Explanation: The special subsequences are [0,1,2,2], [0,1,2,2], and [0,1,2,2].
 * Example 2:
 *
 * Input: nums = [2,2,0,0]
 * Output: 0
 * Explanation: There are no special subsequences in [2,2,0,0].
 * Example 3:
 *
 * Input: nums = [0,1,2,0,1,2]
 * Output: 7
 * Explanation: The special subsequences are:
 * - [0,1,2,0,1,2]
 * - [0,1,2,0,1,2]
 * - [0,1,2,0,1,2]
 * - [0,1,2,0,1,2]
 * - [0,1,2,0,1,2]
 * - [0,1,2,0,1,2]
 * - [0,1,2,0,1,2]
 */
public class CountNumber {
    public static void main(String[] args) {

    }

    public int countSpecialSubsequences(int[] nums) {
        long MOD = 1000000007;
        int row = nums.length;
        long[][] dp = new long[row + 1][4];
        dp[0][0] = 1;
        for (int i = 1; i <= row; i++) {
            dp[i] = dp[i - 1].clone();
            if (nums[i - 1] == 0) {
                dp[i][1] = (int) ((dp[i - 1][1] * 2 + dp[i - 1][0])%MOD);
            }
            if (nums[i - 1] == 1) {
                dp[i][2] = (int) ((dp[i - 1][2] * 2 + dp[i - 1][1])%MOD);
            }
            if (nums[i - 1] == 2) {
                dp[i][3] = (int) ((dp[i - 1][3] * 2 + dp[i - 1][2])%MOD);
            }
        }
        return (int) dp[row][3];
    }

}
