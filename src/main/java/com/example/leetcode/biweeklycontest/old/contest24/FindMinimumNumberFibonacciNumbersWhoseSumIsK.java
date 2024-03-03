package com.example.leetcode.biweeklycontest.old.contest24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Given the number k, return the minimum number of Fibonacci numbers whose sum is equal to k, whether a Fibonacci number could be used multiple times.
 *
 * The Fibonacci numbers are defined as:
 *
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 , for n > 2.
 * It is guaranteed that for the given constraints we can always find such fibonacci numbers that sum k.
 *
 *
 * Example 1:
 *
 * Input: k = 7
 * Output: 2
 * Explanation: The Fibonacci numbers are: 1, 1, 2, 3, 5, 8, 13, ...
 * For k = 7 we can use 2 + 5 = 7.
 * f5 + f3
 * f4 + f3 + f3
 * f3 + f2 + f2 + f1 + f2 + f1
 * f2 + f1 + f2 + f2 + f1 + f2 + f1
 * Example 2:
 *
 * Input: k = 10
 * Output: 2
 * Explanation: For k = 10 we can use 2 + 8 = 10.
 * Example 3:
 *
 * Input: k = 19
 * Output: 3
 * Explanation: For k = 19 we can use 1 + 5 + 13 = 19.
 */
public class FindMinimumNumberFibonacciNumbersWhoseSumIsK {

    public static void main(String[] args) {
        int input = 645157245;
        FindMinimumNumberFibonacciNumbersWhoseSumIsK findMinimumNumberFibonacciNumbersWhoseSumIsK = new FindMinimumNumberFibonacciNumbersWhoseSumIsK();
        int result = findMinimumNumberFibonacciNumbersWhoseSumIsK.findMinFibonacciNumbers(input);
        System.out.println(result);

    }

    public int findMinFibonacciNumbers(int k) {
        List<Integer> fibonacciList = new ArrayList<>();
        fibonacciList.add(1);
        fibonacciList.add(1);
        int index = 1;
        int value;
        int result = 0;
        do {
            value = fibonacciList.get(index) + fibonacciList.get(index -1);
            fibonacciList.add(value);
            index++;
        } while (value <= k);
        Collections.sort(fibonacciList,Comparator.reverseOrder());
        for(int val: fibonacciList){
            if(k>=val){
                k -= val;
                result++;
            }
            if(k==0)
                break;
        }
        return result;
    }


}
