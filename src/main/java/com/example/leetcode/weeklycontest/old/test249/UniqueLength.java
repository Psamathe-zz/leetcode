package com.example.leetcode.weeklycontest.old.test249;


import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
 *
 * Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
 *
 * A palindrome is a string that reads the same forwards and backwards.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 *
 *
 * Example 1:
 *
 * Input: s = "aabca"
 * Output: 3
 * Explanation: The 3 palindromic subsequences of length 3 are:
 * - "aba" (subsequence of "aabca")
 * - "aaa" (subsequence of "aabca")
 * - "aca" (subsequence of "aabca")
 * Example 2:
 *
 * Input: s = "adc"
 * Output: 0
 * Explanation: There are no palindromic subsequences of length 3 in "adc".
 * Example 3:
 *
 * Input: s = "bbcbaba"
 * Output: 4
 * Explanation: The 4 palindromic subsequences of length 3 are:
 * - "bbb" (subsequence of "bbcbaba")
 * - "bcb" (subsequence of "bbcbaba")
 * - "bab" (subsequence of "bbcbaba")
 * - "aba" (subsequence of "bbcbaba")
 *
 */
public class UniqueLength {
    public static void main(String[] args) {
        UniqueLength uniqueLength = new UniqueLength();
        uniqueLength.countPalindromicSubsequence("aabca");
    }


    public int countPalindromicSubsequence(String s) {
        Set<String> set = new HashSet<>();
        char[] chars = s.toCharArray();
        boolean[] find = new boolean[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if(find[chars[i] - 'a'])
                continue;
            for (int j = length - 1; j > i ; j--) {
                if(chars[i] == chars[j]){
                    for (int k = i + 1; k < j; k++) {
                        String temp = "" + chars[i] + chars[k] + chars[j];
                        set.add(temp);
                    }
                    break;
                }
            }
            find[chars[i] - 'a'] = true;
        }
        return set.size();
    }


    /**
     * faster solution
     * @param s
     * @return
     */
    public int countPalindromicSubsequenceV1(String s) {
        int n = s.length();
        boolean[][] hasLeft = new boolean[n][26];
        for (int i = 1; i < n; i++) {
            System.arraycopy(hasLeft[i-1], 0, hasLeft[i], 0, 26);
            hasLeft[i][s.charAt(i-1)-'a'] = true;
        }
        boolean[][] hasRight = new boolean[n][26];
        for (int i = n-2; i >= 0; i--) {
            System.arraycopy(hasRight[i+1], 0, hasRight[i], 0, 26);
            hasRight[i][s.charAt(i+1)-'a'] = true;
        }
        boolean[][] exists = new boolean[26][26];
        for (int i = 1; i < n-1; i++) {
            int c = s.charAt(i) - 'a';
            for (int d = 0; d < 26; d++) {
                if (hasLeft[i][d] && hasRight[i][d]) exists[c][d] = true;
            }
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (exists[i][j]) ans++;
            }
        }
        return ans;
    }
}
