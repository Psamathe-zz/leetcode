package com.example.leetcode.challenge.test2021.february.week2;


/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 * Note:
 * You may assume the string contains only lowercase alphabets.
 *
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class ValidAnagram {
    public static void main(String[] args) {

    }

    public boolean isAnagram(String s, String t) {
        int length1 = s.length();
        int length2 = t.length();
        if(length1 != length2)
            return false;
        int[] count = new int[26];
        for (int i = 0; i < length1; i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if(count[i] != 0)
                return false;
        }
        return true;
    }
}
