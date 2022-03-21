package com.example.leetcode.challenge.test2022.march;

/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 *
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 * Example 2:
 *
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 */
public class IsSubsequence {
    public static void main(String[] args) {

    }

    public boolean isSubsequence(String s, String t) {
        int length1 = s.length();
        int length2 = t.length();
        int index1 = 0;
        int index2 = 0;
        while (index2 < length2) {
            if(index1 < length1 && s.charAt(index1) == t.charAt(index2))
                index1++;
            index2++;
        }
        return index1 == length1;
    }
}
