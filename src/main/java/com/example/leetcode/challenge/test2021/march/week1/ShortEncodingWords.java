package com.example.leetcode.challenge.test2021.march.week1;


import java.util.*;

/**
 * A valid encoding of an array of words is any reference string s and array of indices indices such that:
 *
 * words.length == indices.length
 * The reference string s ends with the '#' character.
 * For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character is equal to words[i].
 * Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["time", "me", "bell"]
 * Output: 10
 * Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
 * words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
 * words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
 * words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
 * Example 2:
 *
 * Input: words = ["t"]
 * Output: 2
 * Explanation: A valid encoding would be s = "t#" and indices = [0].
 */
public class ShortEncodingWords {
    public static void main(String[] args) {
        ShortEncodingWords shortEncodingWords = new ShortEncodingWords();
        shortEncodingWords.minimumLengthEncoding(new String[]{"time", "me", "bell"});
    }

    public int minimumLengthEncoding(String[] words) {
        int res = 0;
        TreeMap<String,Integer> map = new TreeMap<>();
        for (String word : words)
            map.put(word, 1);
        for (String word : map.keySet()) {
            for (int i = 1; i < word.length(); ++i) {
                if(map.containsKey(word.substring(i)))
                map.put(word.substring(i),map.get(word.substring(i)) - 1);
            }
        }
        for (Map.Entry<String,Integer> entry : map.entrySet()){
            if(entry.getValue() > 0)
                res += entry.getKey().length() + 1;
        }
        return res;
    }
}
