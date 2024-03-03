package com.example.leetcode.biweeklycontest.old.contest32;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given a string s. An awesome substring is a non-empty substring of s such that we can make any number of swaps in order to make it palindrome.
 *
 * Return the length of the maximum length awesome substring of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3242415"
 * Output: 5
 * Explanation: "24241" is the longest awesome substring, we can form the palindrome "24142" with some swaps.
 * Example 2:
 *
 * Input: s = "12345678"
 * Output: 1
 * Example 3:
 *
 * Input: s = "213123"
 * Output: 6
 * Explanation: "213123" is the longest awesome substring, we can form the palindrome "231132" with some swaps.
 * Example 4:
 *
 * Input: s = "00"
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists only of digits.
 */
public class FindLongestAwesomeSubstring {
    public static void main(String[] args) {
        String s = "213123";
        FindLongestAwesomeSubstring findLongestAwesomeSubstring = new FindLongestAwesomeSubstring();
        findLongestAwesomeSubstring.longestAwesome(s);
    }

    static int[] pre = new int[1 << 11];
    public int longestAwesome(String s) {
        int n = s.length(), status = 0, res = 0;
        Arrays.fill(pre, -2);  // pre数组初始化为-2，代表都没有出现过
        pre[status] = -1;  // 最初的状态为0，代表都出现了0次（偶数次）
        for (int i = 0; i < n; i++) {
            status ^= 1 << (s.charAt(i) - '0');  // 更新当前状态
            if (pre[status] != -2) {  // 之前已经存在过
                res = Math.max(res, i - pre[status]);
            } else {  // 没有存在过
                pre[status] = i;
            }
            for (int j = 0; j < 10; j++) {  // 枚举0-9
                int status1 = status ^ (1 << j);  // 将对应位置的奇偶性改变
                if (pre[status1] != -2) {  // 之前是否出现过
                    res = Math.max(res, i - pre[status1]);
                }
            }
        }
        return res;
    }

}
