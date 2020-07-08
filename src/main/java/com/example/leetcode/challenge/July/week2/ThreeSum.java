package com.example.leetcode.challenge.July.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 *
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *    Hide Hint #1
 * So, we essentially need to find three numbers x, y, and z such that they add up to the given value. If we fix one of the numbers say x, we are left with the two-sum problem at hand!
 *    Hide Hint #2
 * For the two-sum problem, if we fix one of the numbers, say
 * x
 * , we have to scan the entire array to find the next number
 * y
 * which is
 * value - x
 * where value is the input parameter. Can we change our array somehow so that this search becomes faster?
 *    Hide Hint #3
 * The second train of thought for two-sum is, without changing the array, can we use additional space somehow? Like maybe a hash map to speed up the search?
 */
public class ThreeSum {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/4481576.html
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list;
        Arrays.sort(nums);
        int length = nums.length;
        int target;
        for (int i = 0; i < length - 2; i++) {
            if(nums[i] > 0)
                break;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            target = 0 - nums[i];
            int j = i + 1, k = length - 1;
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                    while (j < k && nums[j] == nums[j + 1])
                        j++;
                    while (j < k && nums[k] == nums[k - 1])
                        k--;
                    j++;
                    k--;
                } else if (nums[j] + nums[k] < target)
                    j++;
                else
                    k--;
            }
        }
        return res;
    }


}
