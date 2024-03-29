package com.example.leetcode.weeklycontest.old.test252;

/**
 * Given an integer n, return true if n has exactly three positive divisors. Otherwise, return false.
 *
 * An integer m is a divisor of n if there exists an integer k such that n = k * m.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: false
 * Explantion: 2 has only two divisors: 1 and 2.
 * Example 2:
 *
 * Input: n = 4
 * Output: true
 * Explantion: 4 has three divisors: 1, 2, and 4.
 *
 */
public class ThreeDivisors {
    public static void main(String[] args) {
        ThreeDivisors threeDivisors = new ThreeDivisors();
        boolean res = threeDivisors.isThree(4);
        System.out.println(res);
    }

    public boolean isThree(int n) {
        double sqrt = Math.sqrt(n);
        if(Math.floor(sqrt) == sqrt){
            int count = 0;
            for (int i = 2; i <= sqrt; i++) {
                if(n % i == 0)
                    count++;
            }
            return count == 1;
        } else
            return false;
    }
}
