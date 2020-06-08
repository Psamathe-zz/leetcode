package com.example.leetcode.medium;

import java.util.*;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
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
 *
 *   https://zhuanlan.zhihu.com/p/24681450
 * ]
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> result = threeSum.threeSum(nums);
        System.out.println(result);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i != 0 && nums[i] == nums[i-1])
                continue;
            int j = i+1,k = len -1;
            while(j < k){
                int sum = nums[i] + nums[j] + nums[k];
                if(sum == 0){
                    result.add(Arrays.asList(nums[i] , nums[j] , nums[k]));
                    while (++j < k && nums[j-1] == nums[j]) {

                    }
                    while (--k > j && nums[k+1] == nums[k]) {

                    }
                } else if(sum < 0){
                    ++j;
                } else{
                    --k;
                }
            }
        }

        return result;
    }


}
