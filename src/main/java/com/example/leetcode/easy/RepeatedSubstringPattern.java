package com.example.leetcode.easy;

/**
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
 * You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 *
 *
 *
 * Example 1:
 *
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 * Example 2:
 *
 * Input: "aba"
 * Output: False
 * Example 3:
 *
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */
public class RepeatedSubstringPattern {
    public static void main(String[] args) {
        String s = "abcabcabcabc";
        RepeatedSubstringPattern repeatedSubstringPattern = new RepeatedSubstringPattern();
        boolean result = repeatedSubstringPattern.repeatedSubstringPatternV2(s);
        System.out.println(result);
        String[]  list = s.split("abc");
        System.out.println(list);
    }

    public boolean repeatedSubstringPattern(String s) {
        int length = s.length();
        String subString;
        for(int i = 1; i <= length / 2; i++){
            if (length % i == 0) {
                int c = length / i;
                subString = "";
                for (int j = 0; j < c; ++j) {
                    subString += s.substring(0, i);
                }
                if (subString.equals(s))
                    return true;
            }
        }
        return false;
    }

    /**
     * https://www.cnblogs.com/grandyang/p/6087347.html
     *
     * KMP 算法
     */
    public boolean repeatedSubstringPatternV2(String str) {
        int i = 1, j = 0, n = str.length();
        int[]dp = new int[n + 1];
        while (i < n) {
            if (str.charAt(i) == str.charAt(j)) {
                ++i;
                ++j;
                dp[i] = j;
            }
            else if (j == 0)
                ++i;
            else
                j = dp[j];
        }
        return (dp[n] != 0) && (dp[n] % (n - dp[n]) == 0);
    }
}
