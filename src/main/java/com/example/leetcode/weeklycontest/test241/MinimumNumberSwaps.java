package com.example.leetcode.weeklycontest.test241;


/**
 * Given a binary string s, return the minimum number of character swaps to make it alternating, or -1 if it is impossible.
 *
 * The string is called alternating if no two adjacent characters are equal. For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 *
 * Any two characters may be swapped, even if they are not adjacent.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "111000"
 * Output: 1
 * Explanation: Swap positions 1 and 4: "111000" -> "101010"
 * The string is now alternating.
 * Example 2:
 *
 * Input: s = "010"
 * Output: 0
 * Explanation: The string is already alternating, no swaps are needed.
 * Example 3:
 *
 * Input: s = "1110"
 * Output: -1
 */
public class MinimumNumberSwaps {
    public static void main(String[] args) {

    }

    public int minSwaps(String s) {
        int n = s.length();
        int num0 = 0;
        int num1 = 0, odd1 = 0;//1的个数
        int ans = n;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                num1++;
                if (i % 2 == 0) {
                    odd1++;
                }
            }
            else {
                num0++;
            }
        }
        if (Math.abs(num0 - num1) > 1) {
            return -1;
        }
        if (n % 2 == 0) {
            ans = Math.min(odd1, n/2 - odd1);
        }
        else {
            if (num0 > num1) {
                ans = odd1;
            }
            else {
                ans = num1 - odd1;
            }
        }
        return ans;
    }
}
