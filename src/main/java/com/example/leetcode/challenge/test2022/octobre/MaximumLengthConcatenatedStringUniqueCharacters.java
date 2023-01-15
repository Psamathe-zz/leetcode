package com.example.leetcode.challenge.test2022.octobre;


import java.util.List;

/**
 * You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of arr that has unique characters.
 *
 * Return the maximum possible length of s.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All the valid concatenations are:
 * - ""
 * - "un"
 * - "iq"
 * - "ue"
 * - "uniq" ("un" + "iq")
 * - "ique" ("iq" + "ue")
 * Maximum length is 4.
 * Example 2:
 *
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
 * Example 3:
 *
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 * Explanation: The only string in arr has all 26 characters.
 */
public class MaximumLengthConcatenatedStringUniqueCharacters {
    public static void main(String[] args) {

    }

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
