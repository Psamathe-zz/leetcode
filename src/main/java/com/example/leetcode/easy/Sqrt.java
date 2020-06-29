package com.example.leetcode.easy;

/**
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 *
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 *
 * Example 1:
 *
 * Input: 4
 * Output: 2
 * Example 2:
 *
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 *              the decimal part is truncated, 2 is returned.
 */
public class Sqrt {
    public static void main(String[] args) {

    }

    public int mySqrt(int x) {
        if(x==1)
            return 1;
        int left = 0;
        int right = x / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long doubled = (long) mid * mid;
            if (doubled > x) {
                right = mid - 1;
            } else if (doubled < x) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return right;
    }
}
