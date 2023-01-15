package com.example.leetcode.challenge.test2022.octobre;

/**
 * Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
 *
 * A string is represented by an array if the array elements concatenated in order forms the string.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
 * Output: true
 * Explanation:
 * word1 represents string "ab" + "c" -> "abc"
 * word2 represents string "a" + "bc" -> "abc"
 * The strings are the same, so return true.
 * Example 2:
 *
 * Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
 * Output: false
 * Example 3:
 *
 * Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
 * Output: true
 */
public class CheckTwoStringArraysAreEquivalent {
    public static void main(String[] args) {

    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int index10 = 0;
        int index11 = 0;
        int index20 = 0;
        int index21 = 0;
        do {
            if(word1[index10].charAt(index11) != word2[index20].charAt(index21)) {
                return false;
            }
            index11++;
            index21++;
            if(index11 == word1[index10].length()) {
                index11 = 0;
                index10++;
            }
            if(index21 == word2[index20].length()) {
                index21 = 0;
                index20++;
            }
        } while (index10 < word1.length && index20 < word2.length);
        return index10 == word1.length && index20 == word2.length &&
                word1[index10].length() == index11 && word2[index20].length() == index21 ;
    }
}
