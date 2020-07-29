package com.example.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

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
 */
public class NthTribonacciNumber {
    public static void main(String[] args) {

    }

    Map<Integer,Integer> map;
    public int tribonacci(int n) {
        map = new HashMap<>();
        map.put(0,0);
        map.put(1,1);
        map.put(2,1);
        return helper(n);
    }

    public int helper(int n){
        if(map.containsKey(n))
            return map.get(n);
        int res = helper(n-1)+helper(n-2)+helper(n-3);
        map.put(n,res);
        return res;
    }

    /**
     * less memory
     * @param n
     * @return
     */
    public int tribonacciV1(int n) {
        int F[] = new int[38];
        F[0]=0;
        F[1]=1;
        F[2]=1;
        for (int i=3; i<=n; i++) {
            F[i] = F[i-1] + F[i-2] + F[i-3];
        }
        return F[n];
    }

}
