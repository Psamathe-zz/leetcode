package com.example.leetcode.medium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * S and T are strings composed of lowercase letters. In S, no letter occurs more than once.
 *
 * S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.
 *
 * Return any permutation of T (as a string) that satisfies this property.
 *
 * Example :
 * Input:
 * S = "cba"
 * T = "abcd"
 * Output: "cbad"
 * Explanation:
 * "a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".
 * Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 *
 *
 * Note:
 *
 * S has length at most 26, and no character is repeated in S.
 * T has length at most 200.
 * S and T consist of lowercase letters only.
 * "kqep"
 * "pekeq"
 * Output
 * "kqep"
 * Expected
 * "kqeep"
 */
public class CustomSortString {
    public static void main(String[] args) {
        CustomSortString customSortString = new CustomSortString();
        String result = customSortString.customSortString("kqep","pekeq");
        System.out.println(result);
    }

    public String customSortString(String S, String T) {
        int[] values = new int[26];

        for (int i = 0; i < S.length(); i++) {
            values[S.charAt(i) - 'a'] = i;
        }

        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < T.length(); i++) {
            map.put(i,values[T.charAt(i) - 'a']);
        }
        List<Integer> list = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(e->e.getKey()).collect(Collectors.toList());
        return list.stream().map(e->T.charAt(e)).map(e->e.toString()).collect(Collectors.joining());
    }


    public String customSortStringV1(String S, String T) {
        int[] count = new int[26];
        for (char c: T.toCharArray())
            count[c - 'a']++;
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            while (count[c- 'a']-- > 0)
                sb.append(c);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            while (count[c - 'a']-- > 0)
                sb.append(c);
        }
        return sb.toString();
    }
}
