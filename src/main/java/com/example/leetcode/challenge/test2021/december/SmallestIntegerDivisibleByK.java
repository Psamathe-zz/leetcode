package com.example.leetcode.challenge.test2021.december;

/**
 * Given a positive integer k, you need to find the length of the smallest positive integer n such that n is divisible by k, and n only contains the digit 1.
 *
 * Return the length of n. If there is no such n, return -1.
 *
 * Note: n may not fit in a 64-bit signed integer.
 *
 *
 *
 * Example 1:
 *
 * Input: k = 1
 * Output: 1
 * Explanation: The smallest answer is n = 1, which has length 1.
 * Example 2:
 *
 * Input: k = 2
 * Output: -1
 * Explanation: There is no such positive integer n divisible by 2.
 * Example 3:
 *
 * Input: k = 3
 * Output: 3
 * Explanation: The smallest answer is n = 111, which has length 3.
 */
public class SmallestIntegerDivisibleByK {
    public static void main(String[] args) {

    }

    public int smallestRepunitDivByK(int K) {
        if(K % 10 != 1 && K % 10 != 3 && K % 10 != 7 && K % 10 != 9)
            return -1;
        else{
            int r = 0;
            for (int i = 1; i <= K + 1 ; i++) {
                r = (10 * r + 1) % K;
                if(r == 0)
                    return i;
            }
            return -1;
        }
    }
}
