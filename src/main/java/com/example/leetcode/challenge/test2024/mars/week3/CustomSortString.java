package com.example.leetcode.challenge.test2024.mars.week3;


import java.util.HashMap;
import java.util.Map;

/**
 * You are given two strings order and s. All the characters of order are unique and were sorted in some custom order previously.
 *
 * Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.
 *
 * Return any permutation of s that satisfies this property.
 *
 *
 *
 * Example 1:
 *
 * Input:  order = "cba", s = "abcd"
 *
 * Output:  "cbad"
 *
 * Explanation: "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
 *
 * Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.
 *
 * Example 2:
 *
 * Input:  order = "bcafg", s = "abcd"
 *
 * Output:  "bcad"
 *
 * Explanation: The characters "b", "c", and "a" from order dictate the order for the characters in s. The character "d" in s does not appear in order, so its position is flexible.
 *
 * Following the order of appearance in order, "b", "c", and "a" from s should be arranged as "b", "c", "a". "d" can be placed at any position since it's not in order. The output "bcad" correctly follows this rule. Other arrangements like "bacd" or "bcda" would also be valid, as long as "b", "c", "a" maintain their order.
 */
public class CustomSortString {
    public static void main(String[] args) {

    }

    public String customSortString(String order, String s) {
        int[] count = new int[26];
        boolean[] done = new boolean[26];
        int val;
        char c ;
        StringBuilder stringBuilder = new StringBuilder();
        for (char cc: s.toCharArray()){
            count[cc - 'a']++;
        }

        for (char cc: order.toCharArray()){
            val = count[cc - 'a'];
            for (int i = 0; i < val; i++) {
                stringBuilder.append(cc);
            }
            done[cc - 'a'] = true;
        }

        for (int i = 0; i < 26; i++) {
            if(!done[i]){
                val = count[i];
                c = (char)( 'a' + i);
                for (int j = 0; j < val; j++) {
                    stringBuilder.append(c);
                }
            }
        }
        return stringBuilder.toString();
    }
}
