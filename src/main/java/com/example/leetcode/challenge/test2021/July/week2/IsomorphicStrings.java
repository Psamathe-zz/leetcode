package com.example.leetcode.challenge.test2021.July.week2;


import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 *"badc"
 * "baba"
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 104
 * t.length == s.length
 * s and t consist of any valid ascii character.
 */
public class IsomorphicStrings {
    public static void main(String[] args) {
        IsomorphicStrings isomorphicStrings = new IsomorphicStrings();
        boolean res = isomorphicStrings.isIsomorphic("badc","baba");
        System.out.println(res);
    }

    public boolean isIsomorphic(String s, String t) {
        Map<Character,Character> map = new HashMap<>();
        int length = s.length();
        char c1;
        char c2;
        for (int i = 0; i < length; i++) {
            c1 = s.charAt(i);
            c2 = t.charAt(i);
            if(map.containsKey(c1)){
                if(c2 != map.get(c1))
                    return false;
            } else {
                if(map.containsValue(c2))
                    return false;
                map.put(c1, c2);
            }
        }
        return true;
    }
}
