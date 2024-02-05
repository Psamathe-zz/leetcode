package com.example.leetcode.weeklycontest.contest2023.test339;

/**
 * You are given a binary string s consisting only of zeroes and ones.
 *
 * A substring of s is considered balanced if all zeroes are before ones and the number of zeroes is equal to the number of ones inside the substring. Notice that the empty substring is considered a balanced substring.
 *
 * Return the length of the longest balanced substring of s.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "01000111"
 * Output: 6
 * Explanation: The longest balanced substring is "000111", which has length 6.
 * Example 2:
 *
 * Input: s = "00111"
 * Output: 4
 * Explanation: The longest balanced substring is "0011", which has length 4.
 * Example 3:
 *
 * Input: s = "111"
 * Output: 0
 * Explanation: There is no balanced substring except the empty substring, so the answer is 0.
 */
public class LongestBalancedSubstring {
    public static void main(String[] args) {
        LongestBalancedSubstring l = new LongestBalancedSubstring();
        l.findTheLongestBalancedSubstring("01000111");
    }

    public int findTheLongestBalancedSubstring(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;

        int res = 0;
        int count0 = 0;
        int count1 = 0;
        for (int i = 0; i < length; i++) {
            if(chars[i] == '0') {
                if(count1 > 0) {
                    res = Math.max(res, Math.min(count0, count1) * 2);
                    count0 = 0;
                    count1 = 0;
                }
                count0++;
            } else {
                count1++;
            }
        }
        res = Math.max(res, Math.min(count0, count1) * 2);

        return res;
    }
}
