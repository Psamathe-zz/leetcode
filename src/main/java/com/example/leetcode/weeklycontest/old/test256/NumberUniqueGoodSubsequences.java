package com.example.leetcode.weeklycontest.old.test256;

/**
 * You are given a binary string binary. A subsequence of binary is considered good if it is not empty and has no leading zeros (with the exception of "0").
 *
 * Find the number of unique good subsequences of binary.
 *
 * For example, if binary = "001", then all the good subsequences are ["0", "0", "1"], so the unique good subsequences are "0" and "1". Note that subsequences "00", "01", and "001" are not good because they have leading zeros.
 * Return the number of unique good subsequences of binary. Since the answer may be very large, return it modulo 109 + 7.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: binary = "001"
 * Output: 2
 * Explanation: The good subsequences of binary are ["0", "0", "1"].
 * The unique good subsequences are "0" and "1".
 * Example 2:
 *
 * Input: binary = "11"
 * Output: 2
 * Explanation: The good subsequences of binary are ["1", "1", "11"].
 * The unique good subsequences are "1" and "11".
 * Example 3:
 *
 * Input: binary = "101"
 * Output: 5
 * Explanation: The good subsequences of binary are ["1", "0", "1", "10", "11", "101"].
 * The unique good subsequences are "0", "1", "10", "11", and "101".
 */
public class NumberUniqueGoodSubsequences {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/number-of-unique-good-subsequences/solution/cpython3java-1er-wei-dp-by-hanxin_hanxin-67lt/
     */
    public int numberOfUniqueGoodSubsequences(String binary)
    {
        int MOD = (int)(1e9 + 7);
        int n = binary.length();
        int [][] dp = new int [n + 1][2];
        for (int i = n - 1; i > -1; i --) {
            if (binary.charAt(i) == '0') {
                dp[i][0] = (dp[i + 1][0] + dp[i + 1][1] + 1) % MOD;
                dp[i][1] = dp[i + 1][1];
            } else {
                dp[i][1] = (dp[i + 1][0] + dp[i + 1][1] + 1) % MOD;
                dp[i][0] = dp[i + 1][0];
            }
        }

        int a = 0;
        if (binary.contains("0") == true)
            a ++;
        return dp[0][1] + a;
    }
}
