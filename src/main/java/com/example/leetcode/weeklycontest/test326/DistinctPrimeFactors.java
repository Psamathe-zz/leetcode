package com.example.leetcode.weeklycontest.test326;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array of positive integers nums, return the number of distinct prime factors in the product of the elements of nums.
 *
 * Note that:
 *
 * A number greater than 1 is called prime if it is divisible by only 1 and itself.
 * An integer val1 is a factor of another integer val2 if val2 / val1 is an integer.
 *
 *
 * Example 1:
 *
 * Input: nums = [2,4,3,7,10,6]
 * Output: 4
 * Explanation:
 * The product of all the elements in nums is: 2 * 4 * 3 * 7 * 10 * 6 = 10080 = 25 * 32 * 5 * 7.
 * There are 4 distinct prime factors so we return 4.
 * Example 2:
 *
 * Input: nums = [2,4,8,16]
 * Output: 1
 * Explanation:
 * The product of all the elements in nums is: 2 * 4 * 8 * 16 = 1024 = 210.
 * There is 1 distinct prime factor so we return 1.
 */
public class DistinctPrimeFactors {
    public static void main(String[] args) {

    }

    public int distinctPrimeFactors(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int val: nums) {
            set.addAll(primeFactorization(val));
        }
        return set.size();
    }

    public List<Integer> primeFactorization(int num) {
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i * i <= num; i++) {
            while (num % i == 0) {
                num = num / i;
                factors.add(i);
            }
        }
        //若最后剩余数不为1，则为最后一个质因数
        if (num != 1) {
            factors.add(num);
        }
        return factors;
    }
}
