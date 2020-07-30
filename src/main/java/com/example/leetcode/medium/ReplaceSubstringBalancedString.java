package com.example.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *You are given a string containing only 4 kinds of characters 'Q', 'W', 'E' and 'R'.
 *
 * A string is said to be balanced if each of its characters appears n/4 times where n is the length of the string.
 *
 * Return the minimum length of the substring that can be replaced with any other string of the same length to make the original string s balanced.
 *
 * Return 0 if the string is already balanced.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "QWER"
 * Output: 0
 * Explanation: s is already balanced.
 * Example 2:
 *
 * Input: s = "QQWE"
 * Output: 1
 * Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is balanced.
 * Example 3:
 *
 * Input: s = "QQQW"
 * Output: 2
 * Explanation: We can replace the first "QQ" to "ER".
 * Example 4:
 *
 * Input: s = "QQQQ"
 * Output: 3
 * Explanation: We can replace the last 3 'Q' to make s = "QWER".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s.length is a multiple of 4
 * s contains only 'Q', 'W', 'E' and 'R'.
 */
public class ReplaceSubstringBalancedString {
    public static void main(String[] args) {
        ReplaceSubstringBalancedString replaceSubstringBalancedString = new ReplaceSubstringBalancedString();
        replaceSubstringBalancedString.balancedString("QWER");
    }

    public int balancedString(String s) {
        int length = s.length();
        int m = length >> 2;
        Map<Character,Integer> map = new HashMap<>();
        for (char c: s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        if (map.getOrDefault('Q',0) == map.getOrDefault('W',0) &&
                map.getOrDefault('W',0) == map.getOrDefault('E',0)&&
                map.getOrDefault('E',0) == map.getOrDefault('R',0))
            return 0;

        int l = 0;
        int r = 0;
        int ans = length;
        while (r < length) {
            map.put(s.charAt(r),map.get(s.charAt(r)) - 1);
            // check outside_of_window[0, l)&(r,L)
            // while window[l,r] replaced for balance, move left-bound 1 step to right
            while (l <= r && map.getOrDefault('Q',0) <= m && map.getOrDefault('W',0) <= m
                    && map.getOrDefault('E',0) <= m && map.getOrDefault('R',0) <= m) {
                ans = Math.min(ans, r - l + 1);
                map.put(s.charAt(l),map.get(s.charAt(l)) + 1);
                l++;
            }
            ++r;
        }

        return ans;
    }


    /**
     * faster solution
     * @param s
     * @return
     */
    public int balancedStringV1(String s) {
        int n = s.length(), target = n/4;
        char[] chs = s.toCharArray();

        int[] counts = new int[128];
        for (char c : chs)
            counts[c]++;

        int l = 0, res = Integer.MAX_VALUE;
        for (int r = 0; r<n; r++) {
            counts[chs[r]]--;
            while(l<n && counts['Q']<=target && counts['W']<=target && counts['E']<=target && counts['R']<=target) {
                res = Math.min(res, r-l+1);
                counts[chs[l]]++;
                l++;
            }
        }
        return res;
    }
}
