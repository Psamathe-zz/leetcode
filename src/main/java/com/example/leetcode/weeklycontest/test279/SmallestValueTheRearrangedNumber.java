package com.example.leetcode.weeklycontest.test279;

/**
 * You are given an integer num. Rearrange the digits of num such that its value is minimized and it does not contain any leading zeros.
 *
 * Return the rearranged number with minimal value.
 *
 * Note that the sign of the number does not change after rearranging the digits.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 310
 * Output: 103
 * Explanation: The possible arrangements for the digits of 310 are 013, 031, 103, 130, 301, 310.
 * The arrangement with the smallest value that does not contain any leading zeros is 103.
 * Example 2:
 *
 * Input: num = -7605
 * Output: -7650
 * Explanation: Some possible arrangements for the digits of -7605 are -7650, -6705, -5076, -0567.
 * The arrangement with the smallest value that does not contain any leading zeros is -7650.
 */
public class SmallestValueTheRearrangedNumber {
    public static void main(String[] args) {

    }

    public long smallestNumber(long num) {
        if(num == 0)
            return 0;
        int[] count = new int[10];
        long res = 0;
        if(num < 0) {
            num = -num;
            while (num > 0 ){
                count[(int)(num % 10)]++;
                num = num / 10;
            }
            for (int i = 9; i >= 0; i--) {
                while (count[i] > 0) {
                    count[i]--;
                    res = res * 10 + i;
                }
            }
            return -res;
        } else {
            while (num > 0 ){
                count[(int)(num % 10)]++;
                num = num / 10;
            }
            for (int i = 1; i <= 9; i++) {
                while (count[i] > 0) {
                    count[i]--;
                    res = res * 10 + i;
                    while (count[0] > 0) {
                        count[0]--;
                        res = res * 10;
                    }
                }
            }
            return res;
        }
    }
}
