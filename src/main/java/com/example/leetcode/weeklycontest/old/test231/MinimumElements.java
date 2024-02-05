package com.example.leetcode.weeklycontest.old.test231;


/**
 * You are given an integer array nums and two integers limit and goal. The array nums has an interesting property that abs(nums[i]) <= limit.
 *
 * Return the minimum number of elements you need to add to make the sum of the array equal to goal. The array must maintain its property that abs(nums[i]) <= limit.
 *
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-1,1], limit = 3, goal = -4
 * Output: 2
 * Explanation: You can add -2 and -3, then the sum of the array will be 1 - 1 + 1 - 2 - 3 = -4.
 * Example 2:
 *
 * Input: nums = [1,-10,9,1], limit = 100, goal = 0
 * Output: 1
 *
 */
public class MinimumElements {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/minimum-elements-to-add-to-form-a-given-sum/solution/5698-gou-cheng-te-ding-he-xu-yao-tian-ji-uqsd/
     * @param nums
     * @param limit
     * @param goal
     * @return
     */
    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int num : nums)
            sum += num;
        long res = 0;
        sum = Math.abs(goal - sum);
        res = (long) Math.ceil(1.0 * sum / limit);
        return (int)res;
    }
}
