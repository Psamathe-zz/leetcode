package com.example.leetcode.weeklycontest.test247;


import java.util.Arrays;
import java.util.HashMap;

/**
 * A wonderful string is a string where at most one letter appears an odd number of times.
 *
 * For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
 * Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return the number of wonderful non-empty substrings in word. If the same substring appears multiple times in word, then count each occurrence separately.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "aba"
 * Output: 4
 * Explanation: The four wonderful substrings are underlined below:
 * - "aba" -> "a"
 * - "aba" -> "b"
 * - "aba" -> "a"
 * - "aba" -> "aba"
 * Example 2:
 *
 * Input: word = "aabb"
 * Output: 9
 * Explanation: The nine wonderful substrings are underlined below:
 * - "aabb" -> "a"
 * - "aabb" -> "aa"
 * - "aabb" -> "aab"
 * - "aabb" -> "aabb"
 * - "aabb" -> "a"
 * - "aabb" -> "abb"
 * - "aabb" -> "b"
 * - "aabb" -> "bb"
 * - "aabb" -> "b"
 * Example 3:
 *
 * Input: word = "he"
 * Output: 2
 * Explanation: The two wonderful substrings are underlined below:
 * - "he" -> "h"
 * - "he" -> "e"
 */
public class NumberWonderfulSubstrings {
    public static void main(String[] args) {
        NumberWonderfulSubstrings numberWonderfulSubstrings = new NumberWonderfulSubstrings();
        numberWonderfulSubstrings.wonderfulSubstrings("aba");
    }

    public long wonderfulSubstrings(String word) {
        HashMap<Integer, Long> cnt = new HashMap<>();
        cnt.put(0,1l);
        long ret = 0l;
        int raw = 0;
        for(int j = 0; j < word.length(); j++){
            int val = 1 << (word.charAt(j) - 'a');
            raw = raw ^ val;
            ret += cnt.getOrDefault(raw, 0l);
            cnt.put(raw, cnt.getOrDefault(raw, 0l) + 1);
            for(int i = 0; i < 10; i++){
                ret += cnt.getOrDefault(raw ^ (1<<i), 0l);
            }
        }
        return ret;
    }
}
