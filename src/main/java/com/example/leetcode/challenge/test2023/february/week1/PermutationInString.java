package com.example.leetcode.challenge.test2023.february.week1;


/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 */
public class PermutationInString {
    public static void main(String[] args) {

    }

    public boolean checkInclusion(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();
        int right;
        int left = 0;
        if(length2 < length1)
            return false;
        int[] map = new int[26];
        for (int i = 0; i < length1; i++) {
            map[s1.charAt(i)-'a']++;
        }
        for (right = 0; right < length2; ++right) {
            if (--map[s2.charAt(right) - 'a'] < 0) {
                while (++map[s2.charAt(left++) -'a'] != 0) {

                }
            } else if (right - left + 1 == length1)
                return true;
        }
        return length1 == 0;
    }
}
