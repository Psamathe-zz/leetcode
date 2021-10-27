package com.example.leetcode.weeklycontest.test264;

import java.util.HashMap;
import java.util.Map;

/**
 * An integer x is numerically balanced if for every digit d in the number x, there are exactly d occurrences of that digit in x.
 *
 * Given an integer n, return the smallest numerically balanced number strictly greater than n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 22
 * Explanation:
 * 22 is numerically balanced since:
 * - The digit 2 occurs 2 times.
 * It is also the smallest numerically balanced number strictly greater than 1.
 * Example 2:
 *
 * Input: n = 1000
 * Output: 1333
 * Explanation:
 * 1333 is numerically balanced since:
 * - The digit 1 occurs 1 time.
 * - The digit 3 occurs 3 times.
 * It is also the smallest numerically balanced number strictly greater than 1000.
 * Note that 1022 cannot be the answer because 0 appeared more than 0 times.
 * Example 3:
 *
 * Input: n = 3000
 * Output: 3133
 * Explanation:
 * 3133 is numerically balanced since:
 * - The digit 1 occurs 1 time.
 * - The digit 3 occurs 3 times.
 * It is also the smallest numerically balanced number strictly greater than 3000.
 */
public class NextGreaterNumerically {
    public static void main(String[] args) {

    }

    public int nextBeautifulNumber(int n) {
        boolean isBeautiful = false;
        while (!isBeautiful) {
            isBeautiful = isBeautifulNumber(++n);
        }
        return n;
    }

    private boolean isBeautifulNumber(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int k = n, num;
        while (k != 0) {
            num = k % 10;
            // 因为数字不超过 10^6，所以 0、8、9 都是不合法的
            if (num == 0 || num == 8 || num == 9) {
                return false;
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
            k = k / 10;
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (!entry.getKey().equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }
}
