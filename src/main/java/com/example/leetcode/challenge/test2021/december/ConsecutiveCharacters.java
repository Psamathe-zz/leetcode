package com.example.leetcode.challenge.test2021.december;

/**
 * The power of the string is the maximum length of a non-empty substring that contains only one unique character.
 *
 * Given a string s, return the power of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode"
 * Output: 2
 * Explanation: The substring "ee" is of length 2 with the character 'e' only.
 * Example 2:
 *
 * Input: s = "abbcccddddeeeeedcba"
 * Output: 5
 * Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
 * Example 3:
 *
 * Input: s = "triplepillooooow"
 * Output: 5
 * Example 4:
 *
 * Input: s = "hooraaaaaaaaaaay"
 * Output: 11
 * Example 5:
 *
 * Input: s = "tourist"
 * Output: 1
 */
public class ConsecutiveCharacters {
    public static void main(String[] args) {

    }

    public int maxPower(String s) {
        int length = s.length();
        char c = s.charAt(0);
        int count = 1;
        int res = 1;
        for (int i = 1; i < length; i++) {
            if(c != s.charAt(i)) {
                res = Math.max(res, count);
                c = s.charAt(i);
                count = 1;
            } else {
                count++;
            }
        }
        res = Math.max(res, count);
        return res;
    }
}
