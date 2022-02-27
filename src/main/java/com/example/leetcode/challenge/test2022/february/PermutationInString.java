package com.example.leetcode.challenge.test2022.february;

/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 */
public class PermutationInString {
    public static void main(String[] args) {

    }

    public boolean checkInclusion(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();
        if(length2 < length1)
            return false;
        int[] map1 = new int[26];
        int[] map2 = new int[26];
        for (int i = 0; i < length1; i++) {
            map1[s1.charAt(i)-'a']++;
            map2[s2.charAt(i)-'a']++;
        }
        if(checkMap(map1,map2))
            return true;
        for (int i = length1; i < length2; i++) {
            map2[s2.charAt(i - length1)-'a']--;
            map2[s2.charAt(i)-'a']++;
            if(checkMap(map1,map2))
                return true;
        }

        return false;
    }
    public boolean checkMap(int[] map1, int[] map2){
        for (int i = 0; i < 26; i++) {
            if(map1[i] != map2[i])
                return false;
        }
        return true;
    }
}
