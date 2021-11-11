package com.example.leetcode.challenge.test2021.octobre;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s, consider all duplicated substrings: (contiguous) substrings of s that occur 2 or more times. The occurrences may overlap.
 *
 * Return any duplicated substring that has the longest possible length. If s does not have a duplicated substring, the answer is "".
 *
 *
 *
 * Example 1:
 *
 * Input: s = "banana"
 * Output: "ana"
 * Example 2:
 *
 * Input: s = "abcd"
 * Output: ""
 */
public class LongestDuplicateSubstring {
    public static void main(String[] args) {

    }

    Long MOD;
    public String longestDupSubstring(String S) {
        int N = S.length();
        int max = 0;
        String ans = "";
        MOD = 1000000007L;
        int l = 0, r = N - 1;
        //binary search

        while (l <= r) {
            int mid = l + (r - l) / 2;
            boolean breakout = false;
            Map<Long, List<Integer>> map = new HashMap<>(); // the List<Integer> store a list of indexes which have the same hashCode
            long prev = 0;
            long pow26 = calcPower26(mid);
            for (int i = 0; i + mid - 1 < N; i++) {
                long code = calc(S, i, mid, prev, pow26); // calculate hashcode
                if (map.containsKey(code)) {
                    for (int m : map.get(code)) { // iterate thru the list and verify the match to deal with hash collision.
                        if (S.substring(i, i + mid).equals(S.substring(m, m + mid))) {
                            breakout = true;
                            if (mid > max) {
                                max = mid;
                                ans = S.substring(i, i + mid);
                            }
                            break;
                        }
                    }
                }
                if (breakout) break;
                map.putIfAbsent(code, new ArrayList<>());
                map.get(code).add(i);
                prev = code; //log prev for next hashcode calculation.
            }
            if (breakout) l = mid + 1;
            else r = mid - 1;
        }
        return ans;
    }
    private long calc(String s, int start, int len, long prev, long pow26) {
        long ans = 0L;
        if (start == 0) { // first time calcuation
            for (int i = 0; i < len; i++) {
                ans *= 26L;
                ans += (long) (s.charAt(start + i) - 'a');
                ans %= MOD;
            }
        } else { // defer from previous result.
            ans = prev * 26L;
            ans += (long) (s.charAt(start + len - 1) - 'a');
            ans -= (s.charAt(start - 1) - 'a') * pow26;    // 在这里写了一个bug, 我直接用ans % pow26了。
        }
        ans %= MOD;
        if (ans < 0L) ans += MOD;
        return ans;
    }
    private long calcPower26 (int n) {
        if (n == 0) return 1L;
        if (n == 1) return 26L;
        long half = calcPower26( n / 2);
        long ans = (half * half) % MOD;
        ans *= calcPower26(n - (n / 2) * 2);
        return ans % MOD;
    }
}
