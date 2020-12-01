package com.example.leetcode.biweeklycontest.contest39;


/**
 * You are given a string s consisting only of characters 'a' and 'b'​​​​.
 *
 * You can delete any number of characters in s to make s balanced. s is balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.
 *
 * Return the minimum number of deletions needed to make s balanced.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aababbab"
 * Output: 2
 * Explanation: You can either:
 * Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
 * Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
 * Example 2:
 *
 * Input: s = "bbaaaaabb"
 * Output: 2
 * Explanation: The only solution is to delete the first two characters.
 *
 */
public class MinimumDeletions {
    public static void main(String[] args) {

    }

    public int minimumDeletions(String s) {
        int len = s.length();
        int[] countA = new int[len + 1];
        int[] countB = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            if (s.charAt(i-1) == 'a') {  // 当前位为a
                countA[i] = countA[i-1];   // a结尾,不需要操作
                countB[i] = countB[i-1] + 1;  // b结尾,a->b 操作数+1
            }else { // 当前位为b
                countA[i] = countA[i-1] + 1; // a结尾,b->a 操作数+1
                countB[i] = Math.min(countA[i-1], countB[i-1]); //b结尾, 取前一个以a/b结尾的最小操作数
            }
        }
        return Math.min(countA[len], countB[len]);
    }

    /**
     * faster solution
     * @param s
     * @return
     */
    public int minimumDeletionsV1(String s) {
        int n = s.length();
        int[] dp = new int[n];
        dp[0] = 0;
        int cntb = s.charAt(0) == 'b' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == 'a') {
                dp[i] = Math.min(dp[i-1] + 1, cntb);
            } else {
                dp[i] = dp[i-1];
                cntb++;
            }
        }
        return dp[n-1];
    }
}
