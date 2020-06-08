package com.example.leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Given an array nums of integers, you can perform operations on the array.
 *
 * In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.
 *
 * You start with 0 points. Return the maximum number of points you can earn by applying such operations.
 *
 * Example 1:
 *
 * Input: nums = [3, 4, 2]
 * Output: 6
 * Explanation:
 * Delete 4 to earn 4 points, consequently 3 is also deleted.
 * Then, delete 2 to earn 2 points. 6 total points are earned.
 *
 *
 * Example 2:
 *
 * Input: nums = [2, 2, 3, 3, 3, 4]
 * Output: 9
 * Explanation:
 * Delete 3 to earn 3 points, deleting both 2's and the 4.
 * Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
 * 9 total points are earned.
 *
 *
 * Note:
 *
 * The length of nums is at most 20000.
 * Each element nums[i] is an integer in the range [1, 10000].
 */
public class DeleteandEarn {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 3, 3, 3, 4};
        DeleteandEarn deleteandEarn = new DeleteandEarn();
        int result = deleteandEarn.deleteAndEarnV1(nums);
        System.out.println(result);
    }

    public int deleteAndEarn(int[] nums) {
        int[] sums = new int[10001];
        int take = 0, skip = 0;
        for (int num : nums)
            sums[num] += num;
        for (int i = 0; i < 10001; ++i) {
            int takei = skip + sums[i];
            int skipi = Math.max(skip, take);
            take = takei;
            skip = skipi;
        }
        return Math.max(skip, take);
    }

    public int deleteAndEarnV1(int[] nums) {
        int[] sums = new int[10001];
        for (int num : nums)
            sums[num] += num;
        for (int i = 2; i < 10001; ++i) {
            sums[i] = Math.max(sums[i - 1], sums[i - 2] + sums[i]);
        }
        return sums[10000];
    }
}
