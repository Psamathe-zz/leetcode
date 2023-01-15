package com.example.leetcode.challenge.test2022.november;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of strings words. Each element of words consists of two lowercase English letters.
 *
 * Create the longest possible palindrome by selecting some elements from words and concatenating them in any order. Each element can be selected at most once.
 *
 * Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.
 *
 * A palindrome is a string that reads the same forward and backward.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["lc","cl","gg"]
 * Output: 6
 * Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
 * Note that "clgglc" is another longest palindrome that can be created.
 * Example 2:
 *
 * Input: words = ["ab","ty","yt","lc","cl","ab"]
 * Output: 8
 * Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
 * Note that "lcyttycl" is another longest palindrome that can be created.
 * Example 3:
 *
 * Input: words = ["cc","ll","xx"]
 * Output: 2
 * Explanation: One longest palindrome is "cc", of length 2.
 * Note that "ll" is another longest palindrome that can be created, and so is "xx".
 */
public class LongestPalindromeConcatenatingTwoLetterWords {
    public static void main(String[] args) {
        LongestPalindromeConcatenatingTwoLetterWords longestPalindromeConcatenatingTwoLetterWords = new LongestPalindromeConcatenatingTwoLetterWords();
        longestPalindromeConcatenatingTwoLetterWords.longestPalindrome(new String[]{"lc","cl","gg"});
    }

    public int longestPalindrome(String[] words) {
        int res = 0;
        Map<String, Integer> count = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        String palindrome;
        Integer val = 0;
        Integer ext = 0;
        for (String word: words) {
            stringBuilder.setLength(0);
            palindrome = stringBuilder.append(word).reverse().toString();
            if(palindrome.equals(word)) {
                val = count.getOrDefault(word, 0);
                if(val == 1) {
                    count.put(word, 0);
                    res += word.length() * 2;
                    ext--;
                } else {
                    count.put(word, 1);
                    ext++;
                }
            } else {
                val = count.getOrDefault(palindrome, 0);
                if (val >= 1) {
                    count.put(palindrome, val - 1);
                    res += word.length() * 2;
                } else {
                    count.put(word, count.getOrDefault(word, 0) + 1);
                }
            }
        }
        if(ext > 0)
            res += 2;
        return res;
    }


}
