package com.example.leetcode.weeklycontest.old.test257;

import com.example.leetcode.algo.UnionFind;

import java.util.Arrays;

/**
 * You are given an integer array nums, and you can perform the following operation any number of times on nums:
 *
 * Swap the positions of two elements nums[i] and nums[j] if gcd(nums[i], nums[j]) > 1 where gcd(nums[i], nums[j]) is the greatest common divisor of nums[i] and nums[j].
 * Return true if it is possible to sort nums in non-decreasing order using the above swap method, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [7,21,3]
 * Output: true
 * Explanation: We can sort [7,21,3] by performing the following operations:
 * - Swap 7 and 21 because gcd(7,21) = 7. nums = [21,7,3]
 * - Swap 21 and 3 because gcd(21,3) = 3. nums = [3,7,21]
 * Example 2:
 *
 * Input: nums = [5,2,6,2]
 * Output: false
 * Explanation: It is impossible to sort the array because 5 cannot be swapped with any other element.
 * Example 3:
 *
 * Input: nums = [10,5,9,3,15]
 * Output: true
 * We can sort [10,5,9,3,15] by performing the following operations:
 * - Swap 10 and 15 because gcd(10,15) = 5. nums = [15,5,9,3,10]
 * - Swap 15 and 3 because gcd(15,3) = 3. nums = [3,5,9,15,10]
 * - Swap 10 and 15 because gcd(10,15) = 5. nums = [3,5,9,10,15]
 */
public class GCDSort {
    public static void main(String[] args) {

    }

    public boolean gcdSort(int[] nums) {
        int len = nums.length;
        int[] sort = nums.clone();
        Arrays.sort(sort);
        int max = sort[len - 1];
        UnionFind uf = new UnionFind(max + 1);

        for (int n : nums) {
            int cur = n;
            for (int i = 2; i <= cur / i; i++) {
                boolean flag = false;
                while (cur % i == 0) {
                    cur /= i;
                    flag = true;
                }
                if (flag) uf.union(i, n);
            }
            if (cur > 1) {
                uf.union(cur, n);
            }

        }

        for (int i = 0; i < nums.length; i++) {
            if (!uf.isConnected(nums[i], sort[i])) {
                return false;
            }
        }
        return true;
    }
}
