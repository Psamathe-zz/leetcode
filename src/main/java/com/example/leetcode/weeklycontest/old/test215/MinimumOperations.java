package com.example.leetcode.weeklycontest.old.test215;


/**
 * You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.
 *
 * Return the minimum number of operations to reduce x to exactly 0 if it's possible, otherwise, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,4,2,3], x = 5
 * Output: 2
 * Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
 * Example 2:
 *
 * Input: nums = [5,6,7,8,9], x = 4
 * Output: -1
 * Example 3:
 *
 * Input: nums = [3,2,20,1,1,3], x = 10
 * Output: 5
 * Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 * 1 <= x <= 109
 */
public class MinimumOperations {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,20,1,1,3};
        int x = 10;
        MinimumOperations minimumOperations = new MinimumOperations();
        minimumOperations.minOperations(nums,x);
    }

    /**
     * https://leetcode-cn.com/problems/minimum-operations-to-reduce-x-to-zero/solution/hua-dong-chuang-kou-by-tufeibaba/
     * @param nums
     * @param x
     * @return
     */
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if(sum == x)
            return nums.length;
        int win = sum - x;
        int w = 0;
        int start = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0;i < nums.length;i++){
            w += nums[i];
            if(w > win){
                while(start < i && w > win){
                    w -= nums[start];
                    start++;
                }
            }
            if(w == win)
                min = Math.min(min,start + nums.length - 1 - i);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

}
