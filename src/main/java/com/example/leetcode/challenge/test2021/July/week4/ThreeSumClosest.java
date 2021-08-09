package com.example.leetcode.challenge.test2021.July.week4;


import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 */
public class ThreeSumClosest {
    public static void main(String[] args) {

    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        return help(nums, target, 3);
    }

    public int help(int[] nums, int target,int numbers){
        int result = Integer.MAX_VALUE;
        if(numbers == 1){
            for (int i = 0; i < nums.length; i++) {
                if(i == 0)
                    result = nums[i];
                if(Math.abs( nums[i] - target) < Math.abs( result - target))
                    result = nums[i];
            }
        } else {
            for (int i = 0; i < nums.length; i++) {
                int temp = nums[i] + help(help2(nums, i), target - nums[i], numbers - 1);
                if(i == 0)
                    result = temp;
                if (Math.abs(temp - target) < Math.abs(result - target))
                    result = temp;
            }
        }
        return result;
    }

    public int[] help2(int[] nums, int target){
        int[] result = new int[nums.length - 1];
        int index = 0;
        for(int i= 0;i<nums.length;i++){
            if(i!=target){
                result[index++] = nums[i];
            }
        }
        return result;
    }


    /**
     * https://www.cnblogs.com/grandyang/p/4510984.html
     * 这道题让返回这个最接近于给定值的值，即要保证当前三数和跟给定值之间的差的绝对值最小，所以需要定义一个变量 diff 用来记录差的绝对值
     * 然后还是要先将数组排个序，然后开始遍历数组，思路跟那道三数之和很相似，都是先确定一个数
     * 然后用两个指针 left 和 right 来滑动寻找另外两个数，每确定两个数，求出此三数之和
     * 然后算和给定值的差的绝对值存在 newDiff 中，然后和 diff 比较并更新 diff 和结果 closest 即可
     * @param nums
     * @param target
     * @return
     */
    int threeSumClosestV1(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];
        int diff = Math.abs(closest - target);
        for (int i = 0; i < nums.length - 2; ++i) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int newDiff = Math.abs(sum - target);
                if (diff > newDiff) {
                    diff = newDiff;
                    closest = sum;
                }
                if (sum < target)
                    ++left;
                else
                    --right;
            }
        }
        return closest;
    }
}
