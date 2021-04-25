package com.example.leetcode.weeklycontest.test238;


/**
 * Given an integer n (in base 10) and a base k, return the sum of the digits of n after converting n from base 10 to base k.
 *
 * After converting, each digit should be interpreted as a base 10 number, and the sum should be returned in base 10.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 34, k = 6
 * Output: 9
 * Explanation: 34 (base 10) expressed in base 6 is 54. 5 + 4 = 9.
 * Example 2:
 *
 * Input: n = 10, k = 10
 * Output: 1
 * Explanation: n is already in base 10. 1 + 0 = 1.
 */
public class SumDigitsK {
    public static void main(String[] args) {
        SumDigitsK sumDigitsK = new SumDigitsK();
        sumDigitsK.sumBase(42, 2);
    }

    public int sumBase(int n, int k) {
        int res = 0;
        int val;
        while ( (val = helper(n, k)) > 1){
            res += n / val;
            n %= val;
        }
        return res + n;

    }

    public int helper(int n, int k){
        int cur = 1;
        while ( cur * k <= n){
            cur *= k;
        }
        return cur;
    }


    /**
     * better solution
     * @param n
     * @param k
     * @return
     */
    public int sumBaseV0(int n, int k) {
        int res = 0;
        while (n > 0){
            res += n%k;
            n /= k;
        }
        return res;
    }
}

