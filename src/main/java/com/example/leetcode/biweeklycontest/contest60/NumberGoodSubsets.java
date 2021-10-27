package com.example.leetcode.biweeklycontest.contest60;

import java.util.*;

/**
 * You are given an integer array nums. We call a subset of nums good if its product can be represented as a product of one or more distinct prime numbers.
 *
 * For example, if nums = [1, 2, 3, 4]:
 * [2, 3], [1, 2, 3], and [1, 3] are good subsets with products 6 = 2*3, 6 = 2*3, and 3 = 3 respectively.
 * [1, 4] and [4] are not good subsets with products 4 = 2*2 and 4 = 2*2 respectively.
 * Return the number of different good subsets in nums modulo 109 + 7.
 *
 * A subset of nums is any array that can be obtained by deleting some (possibly none or all) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: 6
 * Explanation: The good subsets are:
 * - [1,2]: product is 2, which is the product of distinct prime 2.
 * - [1,2,3]: product is 6, which is the product of distinct primes 2 and 3.
 * - [1,3]: product is 3, which is the product of distinct prime 3.
 * - [2]: product is 2, which is the product of distinct prime 2.
 * - [2,3]: product is 6, which is the product of distinct primes 2 and 3.
 * - [3]: product is 3, which is the product of distinct prime 3.
 * Example 2:
 *
 * Input: nums = [4,2,3,15]
 * Output: 5
 * Explanation: The good subsets are:
 * - [2]: product is 2, which is the product of distinct prime 2.
 * - [2,3]: product is 6, which is the product of distinct primes 2 and 3.
 * - [2,15]: product is 30, which is the product of distinct primes 2, 3, and 5.
 * - [3]: product is 3, which is the product of distinct prime 3.
 * - [15]: product is 15, which is the product of distinct primes 3 and 5.
 */
public class NumberGoodSubsets {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/the-number-of-good-subsets/solution/javazhuang-tai-ya-suo-dp-by-puck1609-qlri/
     */
    //预处理30以内的质数，共10种，集合状态用10位二进制表示
    //2、3、5、7、11、13、17、19、23、29
    static final int n = 10;
    static final int mod = (int)1e9 + 7;
    static List<Integer> primeList;
    static Map<Integer, List<Integer>> primeMap;
    static {
        primeList = new ArrayList<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));
        primeMap = new HashMap<>();
        for (int i = 2; i <= 30; i++) {
            int j = 0;
            int pre = -1;
            int n = i;
            while (n > 1) {
                if (n % primeList.get(j) == 0) {
                    n /= primeList.get(j);
                    if (pre == primeList.get(j)) {
                        primeMap.remove(i);
                        break;
                    }
                    pre = primeList.get(j);
                    primeMap.putIfAbsent(i, new ArrayList<>());
                    primeMap.get(i).add(j);
                } else {
                    j++;
                }
            }
        }
    }
    public int numberOfGoodSubsets(int[] nums) {
        int m = nums.length;
        int[] cnt = new int[31];
        for (int i = 0; i < m; i++) {
            cnt[nums[i]]++;
        }
        long[] dp = new long[1 << n];
        dp[0] = 1; // 状态为0表示空集，转移状态用
        for (int i = 2; i <= 30; i++) {
            if (cnt[i] <= 0) continue;
            List<Integer> l = primeMap.get(i);
            if (l == null) continue;
            int numMask = 0;
            for (int factor : l) {
                numMask |= (1 << factor);
            }
            for (int mask = (1 << n) - 1; mask >= 0; mask--) {
                if ((mask & numMask) == 0) {
                    dp[mask | numMask] += dp[mask] * cnt[i];
                    dp[mask | numMask] %= mod;
                }
            }
        }
        long ones = 1, x = cnt[1], base = 2;
        while (x > 0) {
            if ((x & 1) != 0) {
                ones *= base;
                ones %= mod;
            }
            x >>= 1;
            base *= base;
            base %= mod;
        }
        long res = 0;
        // 注意不统计空集
        for (int mask = 1; mask < 1 << n; mask++) {
            res += dp[mask];
            res %= mod;
        }
        return (int)(res * ones % mod);
    }

}
