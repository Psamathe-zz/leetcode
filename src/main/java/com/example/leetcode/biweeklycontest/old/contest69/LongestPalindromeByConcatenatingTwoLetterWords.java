package com.example.leetcode.biweeklycontest.old.contest69;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
 *
 */
public class LongestPalindromeByConcatenatingTwoLetterWords {
    public static void main(String[] args) {
        LongestPalindromeByConcatenatingTwoLetterWords longestPalindromeByConcatenatingTwoLetterWords = new LongestPalindromeByConcatenatingTwoLetterWords();
        longestPalindromeByConcatenatingTwoLetterWords.longestPalindrome(new String[] {"lc","cl","gg"});
    }

    public int longestPalindrome(String[] words) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.compute(word, (k, v) -> {
                if(v == null)
                    return 1;
                else
                    return v + 1;
            });
        }

        int res = 0;
        boolean findMid = false;
        Set<String> visited = new HashSet<>();
        for (String key : count.keySet()) {
            if(key.charAt(0) == key.charAt(1)) {
                if(count.get(key) % 2 == 0) {
                    res += count.get(key) * 2;
                } else {
                    findMid = true;
                    res += count.get(key)/2  * 4;
                }
            } else {
                if(visited.contains(key))
                    continue;
                String revered = new StringBuilder(key).reverse().toString();
                visited.add(revered);
                int v = count.getOrDefault(revered, 0);
                v = Math.min(v, count.get(key));
                res += v * 4;
            }
        }
        if(findMid)
            res += 2;
        return res;
    }
}
