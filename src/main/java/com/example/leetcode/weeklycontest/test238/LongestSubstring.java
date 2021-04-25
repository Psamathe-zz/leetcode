package com.example.leetcode.weeklycontest.test238;


/**
 * A string is considered beautiful if it satisfies the following conditions:
 *
 * Each of the 5 English vowels ('a', 'e', 'i', 'o', 'u') must appear at least once in it.
 * The letters must be sorted in alphabetical order (i.e. all 'a's before 'e's, all 'e's before 'i's, etc.).
 * For example, strings "aeiou" and "aaaaaaeiiiioou" are considered beautiful, but "uaeio", "aeoiu", and "aaaeeeooo" are not beautiful.
 *
 * Given a string word consisting of English vowels, return the length of the longest beautiful substring of word. If no such substring exists, return 0.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "aeiaaioaaaaeiiiiouuuooaauuaeiu"
 * Output: 13
 * Explanation: The longest beautiful substring in word is "aaaaeiiiiouuu" of length 13.
 * Example 2:
 *
 * Input: word = "aeeeiiiioooauuuaeiou"
 * Output: 5
 * Explanation: The longest beautiful substring in word is "aeiou" of length 5.
 * Example 3:
 *
 * Input: word = "a"
 * Output: 0
 * Explanation: There is no beautiful substring, so return 0.
 *
 */
public class LongestSubstring {
    public static void main(String[] args) {
        LongestSubstring longestSubstring = new LongestSubstring();
        longestSubstring.longestBeautifulSubstring("aeiaaioaaaaeiiiiouuuooaauuaeiu");
    }

    public int longestBeautifulSubstring(String word) {
        int length = word.length();
        char[] chars = word.toCharArray();
        int res = 0;
        boolean foundA = false;
        int indexStart = 0;
        int diff = 1;
        for (int i = 0; i < length;) {
            if(!foundA){
                if(chars[i] == 'a') {
                    foundA = true;
                    indexStart = i;
                    diff = 1;
                }
                i++;
            } else{
                if(chars[i] > chars[i-1]) {
                    diff++;
                    i++;
                } else if(chars[i] == chars[i-1]){
                    i++;
                } else {
                    if(diff == 5)
                        res = Math.max(res, i - indexStart);
                    foundA = false;
                }
            }
        }

        if(diff == 5)
            res = Math.max(res, length - indexStart);
        return res;
    }
}
