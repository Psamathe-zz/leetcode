package com.example.leetcode.challenge.test2021.may.week2;


/**
 * Count the number of prime numbers less than a non-negative number, n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * Example 2:
 *
 * Input: n = 0
 * Output: 0
 * Example 3:
 *
 * Input: n = 1
 * Output: 0
 */
public class CountPrimes {
    public static void main(String[] args) {

    }

    public int countPrimes(int n) {
        if(n <=2) {
            return 0;
        }
        int count = n/2;
        boolean[] isPrimes = new boolean[n];
        for(int i=3;i*i<n;i+=2) {
            if(isPrimes[i]) {
                continue;
            }
            for(int j =i*i;j<n;j+=2*i) {
                if(!isPrimes[j]) {
                    isPrimes[j]=true;
                    count--;
                }
            }
        }
        return count;
    }
}
