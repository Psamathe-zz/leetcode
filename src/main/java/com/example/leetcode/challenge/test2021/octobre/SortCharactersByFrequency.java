package com.example.leetcode.challenge.test2021.octobre;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.
 *
 * Return the sorted string. If there are multiple answers, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "tree"
 * Output: "eert"
 * Explanation: 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cccaaa"
 * Output: "aaaccc"
 * Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * Example 3:
 *
 * Input: s = "Aabb"
 * Output: "bbAa"
 * Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 *
 */
public class SortCharactersByFrequency {
    public static void main(String[] args) {

    }

    public String frequencySort(String s) {
        Map<Character, Integer> count = new TreeMap<>();
        for (char c : s.toCharArray()){
            count.compute(c , (k,v) -> v == null? 1: v+1);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(count.entrySet());
        list.sort(Map.Entry.comparingByValue());
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : list){
            for (int i = 0; i < entry.getValue(); i++) {
                stringBuilder.insert(0,entry.getKey());
            }
        }
        return stringBuilder.toString();
    }
}
