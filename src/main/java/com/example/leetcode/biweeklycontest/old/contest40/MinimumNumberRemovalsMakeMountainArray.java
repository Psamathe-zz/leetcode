package com.example.leetcode.biweeklycontest.old.contest40;


import java.util.Arrays;

/**
 * You may recall that an array arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given an integer array nums​​​, return the minimum number of elements to remove to make nums​​​ a mountain array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,1]
 * Output: 0
 * Explanation: The array itself is a mountain array so we do not need to remove any elements.
 * Example 2:
 *
 * Input: nums = [2,1,1,5,6,2,3,1]
 * Output: 3
 * Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
 * Example 3:
 *
 * Input: nums = [4,3,2,1,1,2,3,1]
 * Output: 4
 * Example 4:
 *
 * Input: nums = [1,2,3,4,4,3,2,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 1000
 * 1 <= nums[i] <= 109
 * It is guaranteed that you can make a mountain array out of nums.
 */
public class MinimumNumberRemovalsMakeMountainArray {
    public static void main(String[] args) {

    }

    /**
     * https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-1671-minimum-number-of-removals-to-make-mountain-array/
     * @param nums
     * @return
     */
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] LIS = new int[n];
        int[] LDS = new int[n];
        Arrays.fill(LIS,1); // LIS[i] := Longest increasing subseq ends with nums[i]
        Arrays.fill(LDS,1); // LDS[i] := Longest decreasing subseq starts with nums[i]
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < i; ++j)
                if (nums[i] > nums[j])
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
        for (int i = n - 1; i >= 0; --i)
            for (int j = n - 1; j > i; --j)
                if (nums[i] > nums[j])
                    LDS[i] = Math.max(LDS[i], LDS[j] + 1);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            if (LIS[i] < 2 || LDS[i] < 2)
                continue;
            ans = Math.min(ans, n - (LIS[i] + LDS[i] - 1));
        }
        return ans;
    }
}
