package com.example.leetcode.challenge.test2020.september.week1;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 *
 * Example 1:
 *
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 * Example 2:
 *
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 * Example 3:
 *
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 * Example 4:
 *
 * Input: pattern = "abba", str = "dog dog dog dog"
 * Output: false
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.
 */
public class WordPattern {
    public static void main(String[] args) {
        WordPattern wordPattern = new WordPattern();
        boolean res = wordPattern.wordPattern("abba","dog dog dog dog");
        System.out.println(res);
    }

    public boolean wordPattern(String pattern, String str) {
        String[] res = str.split(" ");
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
