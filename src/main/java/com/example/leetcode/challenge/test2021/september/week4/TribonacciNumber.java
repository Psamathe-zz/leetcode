package com.example.leetcode.challenge.test2021.september.week4;

/**
 * The Tribonacci sequence Tn is defined as follows:
 *
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 *
 * Given n, return the value of Tn.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * Example 2:
 *
 * Input: n = 25
 * Output: 1389537
 *
 */
public class TribonacciNumber {
    public static void main(String[] args) {
        TribonacciNumber tribonacciNumber = new TribonacciNumber();
        int res = tribonacciNumber.tribonacci(25);
        System.out.println(res);
    }

    int[] count;
    public int tribonacci(int n) {
        count = new int[n + 1];
        return helper(n);
    }

    public int helper(int n){

        if(n == 0){
            count[0] = 0;
            return 0;
        } else if(n <= 2) {
            count[n] = 1;
            return 1;
        } else {
            if(count[n] > 0)
                return count[n];
           int val = helper(n -  1) + helper(n - 2) + helper( n - 3);
           count[n] = val;
           return val;
        }
    }

    /**
     * faster solution
     * @param n
     * @return
     */
    public int tribonacciV1(int n) {
        int t0 = 0; int t1 = 1; int t2 = 1;
        if (n <= 1) {return n;}
        if (n == 2) {return 1;}
        if (n == 3) {return 2;}
        int tn = 0;
        for (int i=3; i<=n; i++) {
            tn = t0 + t1 + t2;
            t0 = t1;
            t1 = t2;
            t2 = tn;
        }
        return tn;
    }
}
