package com.example.leetcode.weeklycontest.old.test324;

/**
 * You are given a positive integer n.
 *
 * Continuously replace n with the sum of its prime factors.
 *
 * Note that if a prime factor divides n multiple times, it should be included in the sum as many times as it divides n.
 * Return the smallest value n will take on.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 15
 * Output: 5
 * Explanation: Initially, n = 15.
 * 15 = 3 * 5, so replace n with 3 + 5 = 8.
 * 8 = 2 * 2 * 2, so replace n with 2 + 2 + 2 = 6.
 * 6 = 2 * 3, so replace n with 2 + 3 = 5.
 * 5 is the smallest value n will take on.
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: Initially, n = 3.
 * 3 is the smallest value n will take on.
 */
public class SmallestValue {
    public static void main(String[] args) {

    }

    public int smallestValue(int n) {
        int sum = Integer.MAX_VALUE;
        do {
            sum = getPrimeFactors(n);
            if(sum >= n) {
                break;
            } else {
                n = sum;
            }
        } while (true);

        return n;
    }

    int getPrimeFactors(int val) {
        int sum = 0;

        while (val % 2 == 0) {
            sum += 2;
            val /= 2;
        }


        for (int i = 3; i * i <= val; i += 2) {
            while (val % i == 0) {
                sum += i;
                val /= i;
            }
        }

        if (val > 1) {
            sum += val;
        }
        return sum;
    }
}
