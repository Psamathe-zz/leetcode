package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.
 *
 * Return true if and only if we can do this in a way such that the resulting number is a power of 2.
 *
 *
 *
 * Example 1:
 *
 * Input: 1
 * Output: true
 * Example 2:
 *
 * Input: 10
 * Output: false
 * Example 3:
 *
 * Input: 16
 * Output: true
 * Example 4:
 *
 * Input: 24
 * Output: false
 * Example 5:
 *
 * Input: 46
 * Output: true
 *
 *
 * Note:
 *
 * 1 <= N <= 10^9
 */
public class ReorderedPowerOfTwo {
    public static void main(String[] args) {
        ReorderedPowerOfTwo reorderedPowerOfTwo = new ReorderedPowerOfTwo();
        boolean result = reorderedPowerOfTwo.reorderedPowerOf2(218);
        System.out.println(result);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/10747839.html
     * @param N
     * @return
     */
    public boolean reorderedPowerOf2(int N) {
        char[] chars = String.valueOf(N).toCharArray();
        Arrays.sort(chars);
        String str = String.valueOf(chars);
        int value = 1;
        for (int i = 1; i < 32; i++) {
            chars = String.valueOf(value).toCharArray();
            Arrays.sort(chars);
            if(str.equals(String.valueOf(chars)))
                return true;

            value = value << 1;
        }
        return false;
    }
}
