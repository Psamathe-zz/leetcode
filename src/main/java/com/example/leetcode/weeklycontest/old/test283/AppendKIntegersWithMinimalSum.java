package com.example.leetcode.weeklycontest.old.test283;

import java.util.*;

/**
 * You are given an integer array nums and an integer k. Append k unique positive integers that do not appear in nums to nums such that the resulting total sum is minimum.
 *
 * Return the sum of the k integers appended to nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,4,25,10,25], k = 2
 * Output: 5
 * Explanation: The two unique positive integers that do not appear in nums which we append are 2 and 3.
 * The resulting sum of nums is 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70, which is the minimum.
 * The sum of the two integers appended is 2 + 3 = 5, so we return 5.
 * Example 2:
 *
 * Input: nums = [5,6], k = 6
 * Output: 25
 * Explanation: The six unique positive integers that do not appear in nums which we append are 1, 2, 3, 4, 7, and 8.
 * The resulting sum of nums is 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36, which is the minimum.
 * The sum of the six integers appended is 1 + 2 + 3 + 4 + 7 + 8 = 25, so we return 25.
 * [96,44,99,25,61,84,88,18,19,33,60,86,52,19,32,47,35,50,94,17,29,98,22,21,72,100,40,84]
 * 35
 */
public class AppendKIntegersWithMinimalSum {
    public static void main(String[] args) {
        AppendKIntegersWithMinimalSum appendKIntegersWithMinimalSum = new AppendKIntegersWithMinimalSum();
        appendKIntegersWithMinimalSum.minimalKSum(new int[]{96,44,99,25,61,84,88,18,19,33,60,86,52,19,32,47,35,50,94,17,29,98,22,21,72,100,40,84},
                35);
    }

    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);

        long ans = 0, start = 1;
        for (int i = 0; i < nums.length && k > 0; i++) {
            if (start < nums[i]) {
                int cnt = (int) (nums[i] - start) > k ? k : (int) (nums[i] - start);
                ans += (2L * start + cnt - 1) * cnt / 2;
                k -= cnt;
            }
            start = nums[i] + 1;
        }

        if (k > 0) {
            ans += (2L * start + k - 1) * k / 2;
        }
        return ans;
    }

}
