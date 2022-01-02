package com.example.leetcode.challenge.test2021.december;

/**
 * A positive integer is magical if it is divisible by either a or b.
 *
 * Given the three integers n, a, and b, return the nth magical number. Since the answer may be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1, a = 2, b = 3
 * Output: 2
 * Example 2:
 *
 * Input: n = 4, a = 2, b = 3
 * Output: 6
 * Example 3:
 *
 * Input: n = 5, a = 2, b = 4
 * Output: 10
 * Example 4:
 *
 * Input: n = 3, a = 6, b = 4
 * Output: 8
 */
public class NthMagicalNumber {
    public static void main(String[] args) {

    }

    public int nthMagicalNumber(int n, int a, int b) {
        long lcm = a * b / gcd(a, b);
        long left = 2, right = (long) 1e15;
        int M = 1000000007;
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (mid / a + mid / b - mid / lcm < n)
                left = mid + 1;
            else
                right = mid;
        }
        return (int)(right % M);
    }
    int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}
