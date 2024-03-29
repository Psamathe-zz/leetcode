package com.example.leetcode.weeklycontest.old.test326;

/**
 * You are given a string s consisting of digits from 1 to 9 and an integer k.
 *
 * A partition of a string s is called good if:
 *
 * Each digit of s is part of exactly one substring.
 * The value of each substring is less than or equal to k.
 * Return the minimum number of substrings in a good partition of s. If no good partition of s exists, return -1.
 *
 * Note that:
 *
 * The value of a string is its result when interpreted as an integer. For example, the value of "123" is 123 and the value of "1" is 1.
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 * Example 1:
 *
 * Input: s = "165462", k = 60
 * Output: 4
 * Explanation: We can partition the string into substrings "16", "54", "6", and "2". Each substring has a value less than or equal to k = 60.
 * It can be shown that we cannot partition the string into less than 4 substrings.
 * Example 2:
 *
 * Input: s = "238182", k = 5
 * Output: -1
 * Explanation: There is no good partition for this string.
 */
public class PartitionStringIntoSubstrings {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/partition-string-into-substrings-with-values-at-most-k/solution/by-bei-shui-yan-mei-de-yi-tiao-yu-b0jc/
     * @param s
     * @param k
     * @return
     */
    public int minimumPartition(String s, int k) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            long num = Long.valueOf(s.substring(i, i+1));
            if (k < num) {
                return -1;
            }
        }
        int ans = 0;
        int ptr = 0;
        while (ptr < len) {
            int end = ptr;
            long num = Long.valueOf(s.substring(ptr, end + 1));
            while (num <= k) {
                end++;
                if (end > len) {
                    break;
                }
                num = Long.valueOf(s.substring(ptr, end));
            }
            ptr = end - 1;
            ans += 1;
        }
        return ans;
    }
}
