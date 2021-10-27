package com.example.leetcode.challenge.test2021.september.week4;

import java.util.*;

/**
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
 *
 * Return the maximum possible length of s.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 * Example 2:
 *
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 * Example 3:
 *
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 *
 */
public class MaximumLength {
    public static void main(String[] args) {
        MaximumLength maximumLength = new MaximumLength();
        maximumLength.maxLength(Arrays.asList("cha","r","act","ers"));
    }


    /**
     *
     */
    private int ans;

    public int maxLength(List<String> arr) {
        dfs(arr, 0, new StringBuilder());
        return ans;
    }

    private void dfs(List<String> arr, int start, StringBuilder builder) {
        if (!match(builder)) {
            return;
        }
        ans = Math.max(ans, builder.length());
        int size = arr.size();
        for (int i = start; i < size; i++) {
            String s = arr.get(i);
            builder.append(s);
            dfs(arr, i + 1, builder);
            builder.delete(builder.length() - s.length(), builder.length());
        }
    }

    private boolean match(StringBuilder builder) {
        String s = builder.toString();
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            counter[c - 97]++;
            if (counter[c - 97] > 1) {
                return false;
            }
        }
        return true;
    }

}
