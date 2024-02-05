package com.example.leetcode.weeklycontest.old.test239;

/**
 * Given an integer array nums (0-indexed) and two integers target and start, find an index i such that nums[i] == target and abs(i - start) is minimized. Note that abs(x) is the absolute value of x.
 *
 * Return abs(i - start).
 *
 * It is guaranteed that target exists in nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5], target = 5, start = 3
 * Output: 1
 * Explanation: nums[4] = 5 is the only value equal to target, so the answer is abs(4 - 3) = 1.
 * Example 2:
 *
 * Input: nums = [1], target = 1, start = 0
 * Output: 0
 * Explanation: nums[0] = 1 is the only value equal to target, so the answer is abs(0 - 0) = 1.
 * Example 3:
 *
 * Input: nums = [1,1,1,1,1,1,1,1,1,1], target = 1, start = 0
 * Output: 0
 * Explanation: Every value of nums is 1, but nums[0] minimizes abs(i - start), which is abs(0 - 0) = 0.
 */
public class MinimumDistance {
    public static void main(String[] args) {
        MinimumDistance minimumDistance = new MinimumDistance();
        int res = minimumDistance.getMinDistance(new int[]{1,2,3,4,5},5,3);
        System.out.println(res);
    }

    public int getMinDistance(int[] nums, int target, int start) {
        int diff = 0;
        int length = nums.length;
        while (true){
            if(start + diff >= length && start - diff < 0){
                break;
            }
            if((start + diff < length && nums[start + diff] == target) || (start - diff >= 0 && nums[start - diff] == target))
                return diff;
            diff++;
        }
        return -1;
    }
}
