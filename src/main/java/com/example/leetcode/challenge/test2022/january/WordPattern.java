package com.example.leetcode.challenge.test2022.january;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a pattern and a string s, find if s follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 *
 *
 *
 * Example 1:
 *
 * Input: pattern = "abba", s = "dog cat cat dog"
 * Output: true
 * Example 2:
 *
 * Input: pattern = "abba", s = "dog cat cat fish"
 * Output: false
 * Example 3:
 *
 * Input: pattern = "aaaa", s = "dog cat cat dog"
 * Output: false
 */
public class WordPattern {
    public static void main(String[] args) {

    }

    public boolean wordPattern(String pattern, String s) {
        String[] strings = s.split(" ");
        if(pattern.length() != strings.length)
            return false;
        Map<Character, String> map = new HashMap<>();

        int length = pattern.length();
        for (int i = 0; i < length; i++) {
            if(map.containsKey(pattern.charAt(i))) {
                if(!map.get(pattern.charAt(i)).equals(strings[i])) {
                    return false;
                }
            } else {
                if(map.containsValue(strings[i]))
                    return false;
                map.put(pattern.charAt(i), strings[i]);
            }
        }
        return true;

    }
}
