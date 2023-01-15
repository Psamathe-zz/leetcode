package com.example.leetcode.weeklycontest.test317;

/**
 * You are given two positive integers n and target.
 *
 * An integer is considered beautiful if the sum of its digits is less than or equal to target.
 *
 * Return the minimum non-negative integer x such that n + x is beautiful. The input will be generated such that it is always possible to make n beautiful.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 16, target = 6
 * Output: 4
 * Explanation: Initially n is 16 and its digit sum is 1 + 6 = 7. After adding 4, n becomes 20 and digit sum becomes 2 + 0 = 2. It can be shown that we can not make n beautiful with adding non-negative integer less than 4.
 * Example 2:
 *
 * Input: n = 467, target = 6
 * Output: 33
 * Explanation: Initially n is 467 and its digit sum is 4 + 6 + 7 = 17. After adding 33, n becomes 500 and digit sum becomes 5 + 0 + 0 = 5. It can be shown that we can not make n beautiful with adding non-negative integer less than 33.
 * Example 3:
 *
 * Input: n = 1, target = 1
 * Output: 0
 * Explanation: Initially n is 1 and its digit sum is 1, which is already smaller than or equal to target.
 *
 */
public class MakeIntegerBeautiful {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/minimum-addition-to-make-integer-beautiful/solution/java-by-tizzi-o2xk/
     * @param n
     * @param target
     * @return
     */
    public long makeIntegerBeautiful(long n, int target) {
        for (long k = 1; ; k *= 10) {
            long m = n + (k - n % k) % k;//最后取余k防止为0
            int sum = 0;
            for (long j = m; j != 0; j /= 10) sum += j % 10;
            if (sum <= target) return m - n;
        }
    }
}
