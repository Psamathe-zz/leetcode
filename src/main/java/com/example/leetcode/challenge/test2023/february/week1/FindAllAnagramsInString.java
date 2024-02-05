package com.example.leetcode.challenge.test2023.february.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramsInString {
    public static void main(String[] args) {

    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int[] map = new int[26];
        int[] mapTemp = new int[26];

        int length1 = s.length();
        int length2 = p.length();
        for(int i = 0; i < length2; i++){
            map[p.charAt(i) - 'a']++;
        }
        for (int i = 0; i <= length1 - length2; i++){
            for(int j = 0; j < length2; j++){
                mapTemp[s.charAt(i+j) - 'a']++;
            }
            if(help(map,mapTemp))
                result.add(i);
            Arrays.fill(mapTemp,0);
        }
        return result;
    }

    boolean help(int[] map1, int[] map2){
        for(int i = 0; i<26;i++){
            if(map1[i] != map2[i])
                return false;
        }
        return true;
    }
}
