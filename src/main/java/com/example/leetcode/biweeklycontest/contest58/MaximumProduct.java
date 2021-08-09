package com.example.leetcode.biweeklycontest.contest58;

/**
 * You are given a 0-indexed string s and are tasked with finding two non-intersecting palindromic substrings of odd length such that the product of their lengths is maximized.
 *
 * More formally, you want to choose four integers i, j, k, l such that 0 <= i <= j < k <= l < s.length and both the substrings s[i...j] and s[k...l] are palindromes and have odd lengths. s[i...j] denotes a substring from index i to index j inclusive.
 *
 * Return the maximum possible product of the lengths of the two non-intersecting palindromic substrings.
 *
 * A palindrome is a string that is the same forward and backward. A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ababbb"
 * Output: 9
 * Explanation: Substrings "aba" and "bbb" are palindromes with odd length. product = 3 * 3 = 9.
 * Example 2:
 *
 * Input: s = "zaaaxbbby"
 * Output: 9
 * Explanation: Substrings "aaa" and "bbb" are palindromes with odd length. product = 3 * 3 = 9.
 */
public class MaximumProduct {
    public static void main(String[] args) {

    }

    public long maxProduct(String s) {

        int n = s.length();
        int[] len = new int[n];

        // 马拉车算法 求出以 i 为中心的最大扩展长度
        for(int i = 0, j = -1, mx = -1; i < n; ++i) {
            if(i > mx)
                len[i] = 0;
            else
                len[i] = Math.min(len[2*j - i], mx - i);

            // 中心扩展
            while(i - len[i] - 1 >= 0 && i + len[i] + 1 < n && s.charAt(i - len[i] - 1) == s.charAt(i + len[i] + 1))
                ++len[i];

            if(i + len[i] > mx) {
                mx = i + len[i];
                j = i;
            }
        }

        int[] left = new int[n];/* s[0...i] 的最长奇数回文子串 */
        int[] right = new int[n]; /* s[i...n-1] 的最长奇数回文子串 */;

        // 求出 s[0...i] 的最长奇数回文子串
        left[0] = 1;
        for(int i = 1, p = 0; i < n; ++i) {
            while(p + len[p] < i) ++p;
            left[i] = Math.max(left[i - 1], 2 * (i - p) + 1);
        }

        // 求出 s[i...n-1] 的最长奇数回文子串
        right[n-1] = 1;
        for(int i = n-2, p = n-1; i >= 0; --i) {
            while(p - len[p] > i) --p;
            right[i] = Math.max(right[i + 1], 2*(p - i) + 1);
        }

        // 最终结果
        long ret = 0;
        for(int i = 0; i + 1 < n; ++i)
            ret = Math.max(ret, 1l * left[i] * right[i + 1]);
        return ret;
    }
}
