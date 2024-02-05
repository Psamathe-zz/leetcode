package com.example.leetcode.weeklycontest.old.test229;

/**
 * You are given two strings, word1 and word2. You want to construct a string in the following manner:
 *
 * Choose some non-empty subsequence subsequence1 from word1.
 * Choose some non-empty subsequence subsequence2 from word2.
 * Concatenate the subsequences: subsequence1 + subsequence2, to make the string.
 * Return the length of the longest palindrome that can be constructed in the described manner. If no palindromes can be constructed, return 0.
 *
 * A subsequence of a string s is a string that can be made by deleting some (possibly none) characters from s without changing the order of the remaining characters.
 *
 * A palindrome is a string that reads the same forward as well as backward.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "cacb", word2 = "cbba"
 * Output: 5
 * Explanation: Choose "ab" from word1 and "cba" from word2 to make "abcba", which is a palindrome.
 * Example 2:
 *
 * Input: word1 = "ab", word2 = "ab"
 * Output: 3
 * Explanation: Choose "ab" from word1 and "a" from word2 to make "aba", which is a palindrome.
 * Example 3:
 *
 * Input: word1 = "aa", word2 = "bb"
 * Output: 0
 * Explanation: You cannot construct a palindrome from the described method, so return 0.
 *
 */
public class MaximizePalindromeLength {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/maximize-palindrome-length-from-subsequences/solution/zui-chang-hui-wen-zi-xu-lie-xian-zhi-tia-g6nx/
     * @param word1
     * @param word2
     * @return
     */
    public int longestPalindrome(String word1, String word2) {
        return longestPalindromeSubseq(word1 + word2, word1.length());
    }

    public int longestPalindromeSubseq(String s, int x) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        int max = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (chs[i] == chs[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    if (i < x && j >= x) {
                        max = Math.max(max, dp[i][j]);
                    }
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return max;
    }
}
