package com.example.leetcode.challenge.test2024.january.week1;

import lombok.val;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.
 * <p>
 * Return the sorted string. If there are multiple answers, return any of them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "tree"
 * Output: "eert"
 * Explanation: 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * Example 2:
 * <p>
 * Input: s = "cccaaa"
 * Output: "aaaccc"
 * Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * Example 3:
 * <p>
 * Input: s = "Aabb"
 * Output: "bbAa"
 * Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 */
public class SortCharactersByFrequency {
    public static void main(String[] args) {

    }

    public String frequencySort(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> newMap = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        for (Map.Entry entry : newMap.entrySet()) {
            int times = (Integer) entry.getValue();
            while (times > 0) {
                stringBuffer.append(entry.getKey());
                times--;
            }
        }
        return stringBuffer.reverse().toString();
    }

    /**
     * faster solution
     *
     * @param s
     * @return
     */
    public String frequencySortV2(String s) {


        // Fixed Bucket Size - ASCII Buckets
        // Count each letter in the given string into array of integer bucket.
        // Then for string length find the current max count and insert it into the
        // current position.
        // Time: O(l), Space: O(1)
        char[] freq = new char[128];
        char[] res = new char[s.length()];

        for (char letter : s.toCharArray()) {
            freq[letter]++;
        }

        for (int i = 0; i < s.length(); ) {
            int iMax = 0;
            int counterMax = freq[0];
            for (int j = 1; j < 128; j++) {
                if (counterMax < freq[j]) {
                    counterMax = freq[j];
                    iMax = j;
                }
            }
            while (freq[iMax] > 0) {
                freq[iMax]--;
                res[i] = (char) iMax;
                i++;
            }
        }
        return new String(res);
    }
}
