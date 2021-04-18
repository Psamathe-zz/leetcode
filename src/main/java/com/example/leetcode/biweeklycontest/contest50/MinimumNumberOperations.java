package com.example.leetcode.biweeklycontest.contest50;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given a string s (0-indexed)​​​​​​. You are asked to perform the following operation on s​​​​​​ until you get a sorted string:
 *
 * Find the largest index i such that 1 <= i < s.length and s[i] < s[i - 1].
 * Find the largest index j such that i <= j < s.length and s[k] < s[i - 1] for all the possible values of k in the range [i, j] inclusive.
 * Swap the two characters at indices i - 1​​​​ and j​​​​​.
 * Reverse the suffix starting at index i​​​​​​.
 * Return the number of operations needed to make the string sorted. Since the answer can be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cba"
 * Output: 5
 * Explanation: The simulation goes as follows:
 * Operation 1: i=2, j=2. Swap s[1] and s[2] to get s="cab", then reverse the suffix starting at 2. Now, s="cab".
 * Operation 2: i=1, j=2. Swap s[0] and s[2] to get s="bac", then reverse the suffix starting at 1. Now, s="bca".
 * Operation 3: i=2, j=2. Swap s[1] and s[2] to get s="bac", then reverse the suffix starting at 2. Now, s="bac".
 * Operation 4: i=1, j=1. Swap s[0] and s[1] to get s="abc", then reverse the suffix starting at 1. Now, s="acb".
 * Operation 5: i=2, j=2. Swap s[1] and s[2] to get s="abc", then reverse the suffix starting at 2. Now, s="abc".
 * Example 2:
 *
 * Input: s = "aabaa"
 * Output: 2
 * Explanation: The simulation goes as follows:
 * Operation 1: i=3, j=4. Swap s[2] and s[4] to get s="aaaab", then reverse the substring starting at 3. Now, s="aaaba".
 * Operation 2: i=4, j=4. Swap s[3] and s[4] to get s="aaaab", then reverse the substring starting at 4. Now, s="aaaab".
 * Example 3:
 *
 * Input: s = "cdbea"
 * Output: 63
 * Example 4:
 *
 * Input: s = "leetcodeleetcodeleetcode"
 * Output: 982157772
 */
public class MinimumNumberOperations {
    public static void main(String[] args) {

    }

    /**
     * https://github.com/gzc/leetcode/blob/master/cpp/1001-10000/1821-1830/Minimum%20Number%20of%20Operations%20to%20Make%20String%20Sorted.cpp
     */
    int mod = 1000000007;
    public int makeStringSorted(String s) {
        int[] freq = new int[26];
        for (char ch: s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int n = s.length();
        long[] fact = new long[n+1];
        Arrays.fill(fact,1);
        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i - 1] * i) % mod;
        }

        long ans = 0;
        for (char ch : s.toCharArray()) {
            long freq_sum = 0;
            long duplicates = 1;
            for (int i = 0; i < 26; i++) {
                if (i < (ch - 'a')) {
                    freq_sum += freq[i];
                }
                duplicates = (duplicates * fact[freq[i]]) % mod;
            }
            ans += (freq_sum * fact[n - 1] % mod) * pow(duplicates, mod - 2, mod);
            ans %= mod;
            n--;
            freq[ch - 'a']--;
        }
        return (int)ans;
    }

    long pow(long a, long p, long mod) {
        long ans = 1;
        while (p > 0) {
            if (1==(p & 1)) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
            p = p >> 1;
        }
        return ans;
    }
}
