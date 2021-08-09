package com.example.leetcode.challenge.test2021.august.week1;


/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * Example 2:
 *
 * Input: s = "a"
 * Output: 0
 * Example 3:
 *
 * Input: s = "ab"
 * Output: 1
 */
public class PalindromePartitioningII {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/4271456.html
     * @param s
     * @return
     */
    public int minCut(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int n = s.length();
        boolean[][] p = new boolean[n][n];
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            dp[i] = n - i - 1;
            for (int j = i; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || p[i + 1][j - 1])) {
                    p[i][j] = true;
                    dp[i] = (j == n - 1) ? 0 : Math.min(dp[i], dp[j + 1] + 1);
                }
            }
        }
        return dp[0];
    }
}
