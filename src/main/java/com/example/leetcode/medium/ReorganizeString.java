package com.example.leetcode.medium;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 *
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Example 1:
 *
 * Input: S = "aab"
 * Output: "aba"
 * Example 2:
 *
 * Input: S = "aaab"
 * Output: ""
 * Note:
 *
 * S will consist of lowercase letters and have length in range [1, 500].
 */
public class ReorganizeString {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/8799483.html
     * @param S
     * @return
     */
    public String reorganizeString(String S) {
        int n = S.length();
        char[] res = new char[n];
        int idx = 1;
        int[] count = new int[26];
        int max = 0;
        for (char c: S.toCharArray()){
            count[c-'a'] += 100;
            max = Math.max(max,count[c-'a']);
        }
        for (int i = 0; i < 26; ++i)
            count[i] += i;
        Arrays.sort(count);
        for (int num : count) {
            int t = num / 100;
            char ch = (char) ('a'  + num % 100);
            if (t > (n + 1) / 2)
                return "";
            for (int i = 0; i < t; ++i) {
                if (idx >= n)
                    idx = 0;
                res[idx] = ch;
                idx += 2;
            }
        }
        return new String(res);
    }
}
