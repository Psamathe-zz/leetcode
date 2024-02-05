package com.example.leetcode.weeklycontest.old.test215;


import java.util.*;

/**
 * Two strings are considered close if you can attain one from the other using the following operations:
 *
 * Operation 1: Swap any two existing characters.
 * For example, abcde -> aecdb
 * Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
 * For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
 * You can use the operations on either string as many times as necessary.
 *
 * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "abc", word2 = "bca"
 * Output: true
 * Explanation: You can attain word2 from word1 in 2 operations.
 * Apply Operation 1: "abc" -> "acb"
 * Apply Operation 1: "acb" -> "bca"
 * Example 2:
 *
 * Input: word1 = "a", word2 = "aa"
 * Output: false
 * Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
 * Example 3:
 *
 * Input: word1 = "cabbba", word2 = "abbccc"
 * Output: true
 * Explanation: You can attain word2 from word1 in 3 operations.
 * Apply Operation 1: "cabbba" -> "caabbb"
 * Apply Operation 2: "caabbb" -> "baaccc"
 * Apply Operation 2: "baaccc" -> "abbccc"
 * Example 4:
 *
 * Input: word1 = "cabbba", word2 = "aabbss"
 * Output: false
 * Explanation: It is impossible to attain word2 from word1, or vice versa, in any amount of operations.
 *
 *
 * Constraints:
 *
 * 1 <= word1.length, word2.length <= 105
 * word1 and word2 contain only lowercase English letters.
 */
public class DetermineTwoStringsClose {
    public static void main(String[] args) {
        DetermineTwoStringsClose determineTwoStringsClose = new DetermineTwoStringsClose();
        boolean res = determineTwoStringsClose.closeStrings("cabbba","aabbss");
        System.out.println(res);
    }

    public boolean closeStrings(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        if(length1 != length2){
            return false;
        }

        // 仅当两个字符串字符类别数目、频次数目相等(用排序字符串比较)时返回true
        Map<Character, Integer> map1 = new TreeMap<>();
        Map<Character, Integer> map2 = new TreeMap<>();
        for (int i = 0; i < word1.length(); i++) {
            map1.put(word1.charAt(i), map1.getOrDefault(word1.charAt(i), 0) + 1);
            map2.put(word2.charAt(i), map2.getOrDefault(word2.charAt(i), 0) + 1);
        }
        if (!map1.keySet().toString().equals(map2.keySet().toString())) {
            return false;
        }
        if (!new TreeSet<>(map1.values()).toString().equals(new TreeSet<>(map2.values()).toString())) {
            return false;
        }
        return true;
    }
}
