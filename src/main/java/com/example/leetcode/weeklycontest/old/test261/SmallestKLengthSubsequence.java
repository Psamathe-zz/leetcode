package com.example.leetcode.weeklycontest.old.test261;

/**
 * You are given a string s, an integer k, a letter letter, and an integer repetition.
 *
 * Return the lexicographically smallest subsequence of s of length k that has the letter letter appear at least repetition times. The test cases are generated so that the letter appears in s at least repetition times.
 *
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 *
 * A string a is lexicographically smaller than a string b if in the first position where a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leet", k = 3, letter = "e", repetition = 1
 * Output: "eet"
 * Explanation: There are four subsequences of length 3 that have the letter 'e' appear at least 1 time:
 * - "lee" (from "leet")
 * - "let" (from "leet")
 * - "let" (from "leet")
 * - "eet" (from "leet")
 * The lexicographically smallest subsequence among them is "eet".
 * Example 2:
 *
 * example-2
 * Input: s = "leetcode", k = 4, letter = "e", repetition = 2
 * Output: "ecde"
 * Explanation: "ecde" is the lexicographically smallest subsequence of length 4 that has the letter "e" appear at least 2 times.
 * Example 3:
 *
 * Input: s = "bb", k = 2, letter = "b", repetition = 2
 * Output: "bb"
 * Explanation: "bb" is the only subsequence of length 2 that has the letter "b" appear at least 2 times.
 *
 */
public class SmallestKLengthSubsequence {
    public static void main(String[] args) {

    }


    /**
     * https://leetcode-cn.com/problems/smallest-k-length-subsequence-with-occurrences-of-a-letter/solution/shuang-bai-by-binger-s-3bbj/
     * @param s
     * @param k
     * @param letter
     * @param repetition
     * @return
     */
    public String smallestSubsequence(String s, int k, char letter, int repetition) {
        int count = 0;
        for (char c : s.toCharArray()) {
            count += c == letter ? 1 : 0;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); count -= s.charAt(i++) == letter ? 1 : 0) {
            while (sb.length() + s.length() > i + k && sb.length() > 0
                    && s.charAt(i) < sb.charAt(sb.length() - 1)
                    && (sb.charAt(sb.length() - 1) != letter || count != repetition)) {
                repetition += sb.charAt(sb.length() - 1) == letter ? 1 : 0;
                sb.deleteCharAt(sb.length() - 1);
            }
            if (k - sb.length() > Math.max(0, s.charAt(i) == letter ? 0 : repetition)) {
                sb.append(s.charAt(i));
                repetition -= s.charAt(i) == letter ? 1 : 0;
            }
        }
        return "" + sb;
    }
}
