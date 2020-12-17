package com.example.leetcode.biweeklycontest.contest41;


/**
 * You are given a string allowed consisting of distinct characters and an array of strings words. A string is consistent if all characters in the string appear in the string allowed.
 *
 * Return the number of consistent strings in the array words.
 *
 *
 *
 * Example 1:
 *
 * Input: allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
 * Output: 2
 * Explanation: Strings "aaab" and "baa" are consistent since they only contain characters 'a' and 'b'.
 * Example 2:
 *
 * Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
 * Output: 7
 * Explanation: All strings are consistent.
 * Example 3:
 *
 * Input: allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
 * Output: 4
 * Explanation: Strings "cc", "acd", "ac", and "d" are consistent.
 */
public class CountNumberConsistentStrings {
    public static void main(String[] args) {

    }

    public int countConsistentStrings(String allowed, String[] words) {
        int res = 0;
        boolean[] allow = new boolean[26];

        for (int i = 0; i < allowed.length() ; i++) {
            allow[allowed.charAt(i) - 'a'] = true;
        }
        boolean current;
        for (String word : words){
            current = true;
            for (int i = 0; i < word.length() ; i++) {
                if( !allow[word.charAt(i) - 'a'] ){
                    current = false;
                    break;
                }
            }
            if(current)
                res++;
        }

        return res;
    }
}
