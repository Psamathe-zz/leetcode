package com.example.leetcode.weeklycontest.old.test259;


import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a string s of length n, and an integer k. You are tasked to find the longest subsequence repeated k times in string s.
 *
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 *
 * A subsequence seq is repeated k times in the string s if seq * k is a subsequence of s, where seq * k represents a string constructed by concatenating seq k times.
 *
 * For example, "bba" is repeated 2 times in the string "bababcba", because the string "bbabba", constructed by concatenating "bba" 2 times, is a subsequence of the string "bababcba".
 * Return the longest subsequence repeated k times in string s. If multiple such subsequences are found, return the lexicographically largest one. If there is no such subsequence, return an empty string.
 *
 *
 *
 * Example 1:
 *
 * example 1
 * Input: s = "letsleetcode", k = 2
 * Output: "let"
 * Explanation: There are two longest subsequences repeated 2 times: "let" and "ete".
 * "let" is the lexicographically largest one.
 * Example 2:
 *
 * Input: s = "bb", k = 2
 * Output: "b"
 * Explanation: The longest subsequence repeated 2 times is "b".
 * Example 3:
 *
 * Input: s = "ab", k = 2
 * Output: ""
 * Explanation: There is no subsequence repeated 2 times. Empty string is returned.
 * Example 4:
 *
 * Input: s = "bbabbabbbbabaababab", k = 3
 * Output: "bbbb"
 * Explanation: The longest subsequence "bbbb" is repeated 3 times in "bbabbabbbbabaababab".
 */
public class LongestSubsequenceRepeatedkTimes {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/longest-subsequence-repeated-k-times/solution/java-mei-ju-bfsjian-zhi-by-avlgood2018-jnfb/
     * @param s
     * @param k
     * @return
     */
    public String longestSubsequenceRepeatedK(String s, int k) {
        String res = "";
        // q保存只放可行子串
        Queue<String> q = new LinkedList<>();
        q.add("");
        while (!q.isEmpty()) {
            int size = q.size();
            // 按层BFS，同层长度相同
            while (size-- > 0) {
                String cur = q.poll();
                for (int i = 0; i < 26; i++) {
                    String next = cur + (char) ('a' + i);
                    if (isSub(s, next, k)) {
                        // 找最大的，所以要更新
                        res = next;
                        q.add(next);
                    }
                }
            }
        }
        return res;
    }

    //   sub * k 是否 s的子串.
    private boolean isSub(String s, String sub, int k) {
        int j = 0;
        int repeat = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == sub.charAt(j)) {
                j++;
                if (j == sub.length()) {
                    repeat++;
                    if (repeat == k) {
                        return true;
                    }
                    j = 0;
                }
            }
        }
        return false;
    }
}
