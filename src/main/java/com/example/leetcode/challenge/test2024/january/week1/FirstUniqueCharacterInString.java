package com.example.leetcode.challenge.test2024.january.week1;

import lombok.val;

/**
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leetcode"
 * Output: 0
 * Example 2:
 * <p>
 * Input: s = "loveleetcode"
 * Output: 2
 * Example 3:
 * <p>
 * Input: s = "aabb"
 * Output: -1
 */
public class FirstUniqueCharacterInString {
    public static void main(String[] args) {
        FirstUniqueCharacterInString firstUniqueCharacterInString = new FirstUniqueCharacterInString();
        val res = firstUniqueCharacterInString.firstUniqChar("loveleetcode");
        System.out.println(res);
    }

    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        int[] find = new int[26];
        for (char c : chars) {
            find[c - 'a']++;
        }
        int index = 0;
        for (char c : chars) {
            if (find[c - 'a'] == 1)
                return index;
            else
                index++;
        }
        return -1;
    }
}
