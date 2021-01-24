package com.example.leetcode.challenge.test2021.January.week3;


/**
 * Given a string s, return the longest palindromic substring in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 * Example 3:
 *
 * Input: s = "a"
 * Output: "a"
 * Example 4:
 *
 * Input: s = "ac"
 * Output: "a"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters (lower-case and/or upper-case),
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/4464476.html
     * @param s
     * @return
     */
    int start;
    int maxLen;
    public String longestPalindrome(String s) {
        if (s.length() < 2)
            return s;
        int n = s.length();
        maxLen = 0;
        start = 0;
        for (int i = 0; i < n - 1; ++i) {
            searchPalindrome(s, i, i);
            searchPalindrome(s, i, i + 1);
        }
        return s.substring(start, start + maxLen);
    }
    void searchPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        if (maxLen < right - left - 1) {
            start = left + 1;
            maxLen = right - left - 1;
        }
    }
}
