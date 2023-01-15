package com.example.leetcode.challenge.test2023.january.week1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        WordPattern wordPattern = new WordPattern();
        boolean b = wordPattern.wordPattern("abba", "dog dog dog dog");
        System.out.println(b);
    }

    public boolean wordPattern(String pattern, String s) {
        String[] res = s.split(" ");
        char[] chars = pattern.toCharArray();
        Map<Character,String> map = new HashMap<>();
        Map<String,Character> mapReverse = new HashMap<>();

        if(res.length != chars.length)
            return false;

        int length = res.length;

        for (int i = 0; i < length; i++) {
            if(map.containsKey(chars[i])){
                if(!map.get(chars[i]).equals(res[i]))
                    return false;
            } else if(mapReverse.containsKey(res[i])){
                if(!mapReverse.get(res[i]).equals(chars[i]))
                    return false;
            } else {
                map.put(chars[i],res[i]);
                mapReverse.put(res[i],chars[i]);
            }
        }
        return true;
    }
}
