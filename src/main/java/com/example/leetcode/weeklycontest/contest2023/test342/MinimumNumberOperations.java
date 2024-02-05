package com.example.leetcode.weeklycontest.contest2023.test342;

/**
 * You are given a 0-indexed array nums consisiting of positive integers. You can do the following operation on the array any number of times:
 *
 * Select an index i such that 0 <= i < n - 1 and replace either of nums[i] or nums[i+1] with their gcd value.
 * Return the minimum number of operations to make all elements of nums equal to 1. If it is impossible, return -1.
 *
 * The gcd of two integers is the greatest common divisor of the two integers.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,6,3,4]
 * Output: 4
 * Explanation: We can do the following operations:
 * - Choose index i = 2 and replace nums[2] with gcd(3,4) = 1. Now we have nums = [2,6,1,4].
 * - Choose index i = 1 and replace nums[1] with gcd(6,1) = 1. Now we have nums = [2,1,1,4].
 * - Choose index i = 0 and replace nums[0] with gcd(2,1) = 1. Now we have nums = [1,1,1,4].
 * - Choose index i = 2 and replace nums[3] with gcd(1,4) = 1. Now we have nums = [1,1,1,1].
 * Example 2:
 *
 * Input: nums = [2,10,6,14]
 * Output: -1
 * Explanation: It can be shown that it is impossible to make all the elements equal to 1.
 *
 */
public class MinimumNumberOperations {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1/solution/liang-chong-fang-fa-bao-li-mei-ju-li-yon-refp/
     * @param nums
     * @return
     */
    public int minOperations(int[] nums) {
        int n = nums.length, gcdAll = 0, cnt1 = 0;
        for (int x : nums) {
            gcdAll = gcd(gcdAll, x);
            if (x == 1) ++cnt1;
        }
        if (gcdAll > 1) return -1;
        if (cnt1 > 0) return n - cnt1;

        int minSize = n;
        for (int i = 0; i < n; ++i) {
            int g = 0;
            for (int j = i; j < n; ++j) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    // 这里本来是 j-i+1，把 +1 提出来合并到 return 中
                    minSize = Math.min(minSize, j - i);
                    break;
                }
            }
        }
        return minSize + n - 1;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }
}
