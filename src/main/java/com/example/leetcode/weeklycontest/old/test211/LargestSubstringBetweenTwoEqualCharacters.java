package com.example.leetcode.weeklycontest.old.test211;

import java.util.Arrays;

/**
 * Given a string s, return the length of the longest substring between two equal characters, excluding the two characters. If there is no such substring return -1.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aa"
 * Output: 0
 * Explanation: The optimal substring here is an empty substring between the two 'a's.
 * Example 2:
 *
 * Input: s = "abca"
 * Output: 2
 * Explanation: The optimal substring here is "bc".
 * Example 3:
 *
 * Input: s = "cbzxy"
 * Output: -1
 * Explanation: There are no characters that appear twice in s.
 * Example 4:
 *
 * Input: s = "cabbac"
 * Output: 4
 * Explanation: The optimal substring here is "abba". Other non-optimal substrings include "bb" and "".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 300
 * s contains only lowercase English letters.
 */
public class LargestSubstringBetweenTwoEqualCharacters {
    public static void main(String[] args) {
        LargestSubstringBetweenTwoEqualCharacters largestSubstringBetweenTwoEqualCharacters = new LargestSubstringBetweenTwoEqualCharacters();
        int res = largestSubstringBetweenTwoEqualCharacters.maxLengthBetweenEqualCharacters("aa");
        System.out.println(res);
    }

    public int maxLengthBetweenEqualCharacters(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        int[] start = new int[26];
        Arrays.fill(start,-1);
        int[] current = new int[26];
        int res = -1;
        for (int i = 0; i < length; i++) {
            if(start[chars[i] - 'a'] == -1){
                start[chars[i] - 'a'] = i;
            } else {
                current[chars[i] - 'a'] = i;
                res = Math.max(current[chars[i] - 'a'] - start[chars[i] - 'a'] - 1,res);
            }
        }
        return res;
    }
}
