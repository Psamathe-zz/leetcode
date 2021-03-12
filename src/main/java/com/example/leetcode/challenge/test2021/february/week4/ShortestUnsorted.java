package com.example.leetcode.challenge.test2021.february.week4;


import java.util.Arrays;

/**
 * Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.
 *
 * Return the shortest such subarray and output its length.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,6,4,8,10,9,15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: 0
 * Example 3:
 *
 * Input: nums = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 * [2,3,3,2,4]
 *
 *
 * Follow up: Can you solve it in O(n) time complexity?
 */
public class ShortestUnsorted {
    public static void main(String[] args) {
        ShortestUnsorted shortestUnsorted = new ShortestUnsorted();
        int res = shortestUnsorted.findUnsortedSubarray(new int[]{2,6,4,8,10,9,15});
        System.out.println(res);
    }

    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length, i = 0, j = n - 1;
        int[] t = Arrays.copyOf(nums,n);
        Arrays.sort(t);
        while (i < n && nums[i] == t[i]) ++i;
        while (j > i && nums[j] == t[j]) --j;
        return j - i + 1;
    }

    int findUnsortedSubarrayV0(int[] nums) {
        int n = nums.length, start = -1, end = -2;
        int mn = nums[n - 1], mx = nums[0];
        for (int i = 1; i < n; ++i) {
            mx = Math.max(mx, nums[i]);
            mn = Math.min(mn, nums[n - 1 - i]);
            if (mx > nums[i])
                end = i;
            if (mn < nums[n - 1 - i])
                start = n - 1 - i;
        }
        return end - start + 1;
    }
}
