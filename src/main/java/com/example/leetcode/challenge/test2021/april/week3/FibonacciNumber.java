package com.example.leetcode.challenge.test2021.april.week3;

import java.util.HashMap;
import java.util.Map;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 * Example 2:
 *
 * Input: n = 3
 * Output: 2
 * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
 * Example 3:
 *
 * Input: n = 4
 * Output: 3
 * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 *
 *
 * Constraints:
 *
 * 0 <= n <= 30
 */
public class FibonacciNumber {
    public static void main(String[] args) {
        FibonacciNumber fibonacciNumber = new FibonacciNumber();
        int res = fibonacciNumber.fib(2);
        System.out.println(res);
    }

    Map<Integer,Integer> map = new HashMap<>();
    public int fib(int n) {
        if(map.containsKey(n))
            return map.get(n);
        else {
            int res;
            if(n == 1){
                res = 1;
            } else if(n == 0) {
                res = 0;
            } else {
                res = fib(n - 1) + fib(n - 2);
            }
            map.put(n, res);
            return res;
        }
    }
}
