package com.example.leetcode.challenge.test2021.July.week3;


import java.util.*;

/**
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 *
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * Example 2:
 *
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 *
 */
public class FourSum {
    public static void main(String[] args) {
        FourSum fourSum = new FourSum();
        fourSum.fourSum(new int[]{2,2,2,2,2},8);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int val;
        Arrays.sort(nums);
        Set<List<Integer>> res= new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            val = nums[i];
            List<List<Integer>> l = threeSum(nums, target - val, i);
            for(List<Integer> list : l){
                list.add(val);
                res.add(list);
            }
        }
        return new ArrayList<>(res);
    }

    public List<List<Integer>> threeSum(int[] nums, int target, int start){
        Set<List<Integer>> res= new HashSet<>();
        int val;
        for (int i = start + 1; i < nums.length; i++) {
            val = nums[i];
            List<List<Integer>> l = twoSum(nums, target - val, i);
            for(List<Integer> list : l){
                list.add(val);
                res.add(list);
            }
        }
        return new ArrayList<>(res);
    }


    public List<List<Integer>> twoSum(int[] nums, int target, int start){
        Set<List<Integer>> res= new HashSet<>();
        for (int i = start + 1; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    res.add(list);
                } else if(nums[i] + nums[j] > target)
                    break;

            }
        }
        return new ArrayList<>(res);
    }


    public List<List<Integer>> fourSumV1(int[] nums, int target) {
        List<List<Integer>> res= new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; ++i) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < n - 2; ++j) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int left = j + 1, right = n - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        List<Integer> out = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        res.add(out);
                        while (left < right && nums[left] == nums[left + 1])
                            ++left;
                        while (left < right && nums[right] == nums[right - 1])
                            --right;
                        ++left;
                        --right;
                    } else if (sum < target)
                        ++left;
                    else
                        --right;
                }
            }
        }
        return res;
    }
}
