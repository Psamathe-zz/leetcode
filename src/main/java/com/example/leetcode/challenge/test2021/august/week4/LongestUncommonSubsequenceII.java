package com.example.leetcode.challenge.test2021.august.week4;

/**
 * Given an array of strings strs, return the length of the longest uncommon subsequence between them. If the longest uncommon subsequence does not exist, return -1.
 *
 * An uncommon subsequence between an array of strings is a string that is a subsequence of one string but not the others.
 *
 * A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.
 *
 * For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to get "abc". Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).
 *
 *
 * Example 1:
 *
 * Input: strs = ["aba","cdc","eae"]
 * Output: 3
 * Example 2:
 *
 * Input: strs = ["aaa","aaa","aa"]
 * Output: -1
 */
public class LongestUncommonSubsequenceII {
    public static void main(String[] args) {

    }

    public int findLUSlength(String[] strs) {
        int res = -1, j = 0, n = strs.length;
        for (int i = 0; i < n; ++i) {
            for (j = 0; j < n; ++j) {
                if (i == j) continue;
                if (checkSubs(strs[i], strs[j])) break;
            }
            if (j == n) res = Math.max(res, (int)strs[i].length());
        }
        return res;
    }
    boolean checkSubs(String subs, String str) {
        int i = 0;
        for (char c : str.toCharArray()) {
            if (c == subs.charAt(i))
                ++i;
            if (i == subs.length())
                break;
        }
        return i == subs.length();
    }
}
