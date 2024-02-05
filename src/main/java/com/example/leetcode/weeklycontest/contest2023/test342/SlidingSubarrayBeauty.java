package com.example.leetcode.weeklycontest.contest2023.test342;

/**
 * Given an integer array nums containing n integers, find the beauty of each subarray of size k.
 *
 * The beauty of a subarray is the xth smallest integer in the subarray if it is negative, or 0 if there are fewer than x negative integers.
 *
 * Return an integer array containing n - k + 1 integers, which denote the beauty of the subarrays in order from the first index in the array.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-1,-3,-2,3], k = 3, x = 2
 * Output: [-1,-2,-2]
 * Explanation: There are 3 subarrays with size k = 3.
 * The first subarray is [1, -1, -3] and the 2nd smallest negative integer is -1.
 * The second subarray is [-1, -3, -2] and the 2nd smallest negative integer is -2.
 * The third subarray is [-3, -2, 3] and the 2nd smallest negative integer is -2.
 * Example 2:
 *
 * Input: nums = [-1,-2,-3,-4,-5], k = 2, x = 2
 * Output: [-1,-2,-3,-4]
 * Explanation: There are 4 subarrays with size k = 2.
 * For [-1, -2], the 2nd smallest negative integer is -1.
 * For [-2, -3], the 2nd smallest negative integer is -2.
 * For [-3, -4], the 2nd smallest negative integer is -3.
 * For [-4, -5], the 2nd smallest negative integer is -4.
 * Example 3:
 *
 * Input: nums = [-3,1,2,-3,0,-3], k = 2, x = 1
 * Output: [-3,0,-3,-3,-3]
 * Explanation: There are 5 subarrays with size k = 2.
 * For [-3, 1], the 1st smallest negative integer is -3.
 * For [1, 2], there is no negative integer so the beauty is 0.
 * For [2, -3], the 1st smallest negative integer is -3.
 * For [-3, 0], the 1st smallest negative integer is -3.
 * For [0, -3], the 1st smallest negative integer is -3.
 */
public class SlidingSubarrayBeauty {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/sliding-subarray-beauty/solution/hua-dong-chuang-kou-bao-li-mei-ju-by-end-9mvl/
     * @param nums
     * @param k
     * @param x
     * @return
     */
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        final int BIAS = 50;
        var cnt = new int[BIAS * 2 + 1];
        int n = nums.length;
        for (int i = 0; i < k - 1; ++i) // 先往窗口内添加 k-1 个数
            ++cnt[nums[i] + BIAS];
        var ans = new int[n - k + 1];
        for (int i = k - 1; i < n; ++i) {
            ++cnt[nums[i] + BIAS]; // 进入窗口（保证窗口有恰好 k 个数）
            int left = x;
            for (int j = 0; j < BIAS; ++j) { // 暴力枚举负数范围 [-50,-1]
                left -= cnt[j];
                if (left <= 0) { // 找到美丽值
                    ans[i - k + 1] = j - BIAS;
                    break;
                }
            }
            --cnt[nums[i - k + 1] + BIAS]; // 离开窗口
        }
        return ans;
    }
}
