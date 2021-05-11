package com.example.leetcode.challenge.test2021.may.week1;


import java.util.TreeMap;

/**
 * Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one element.
 *
 * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,3]
 * Output: true
 * Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
 * Example 2:
 *
 * Input: nums = [4,2,1]
 * Output: false
 * Explanation: You can't get a non-decreasing array by modify at most one element.
 */
public class NonDecreasingArray {
    public static void main(String[] args) {
        NonDecreasingArray nonDecreasingArray = new NonDecreasingArray();
        nonDecreasingArray.checkPossibility(new int[]{3,4,2,3});
    }

    public boolean checkPossibility(int[] nums) {
        int length = nums.length;
        int count1 = 0;

        int pre = nums[0];
        for (int i = 1; i < length; i++) {
            if(nums[i] < pre){
                count1++;
                if(count1 > 1)
                    break;
            } else {
                pre = nums[i];
            }
        }
        int count2 = 0;
        int sui = nums[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            if(nums[i] > sui){
                count2++;
                if(count2 > 1)
                    break;
            } else {
                sui = nums[i];
            }
        }
        return count2 <= 1 || count1 <= 1;
    }

    /**
     * faster solution
     * @param nums
     * @return
     */
    public boolean checkPossibilityV0(int[] nums) {
        int count=0;
        int n=nums.length;
        for(int i=0;i<n-1;i++) {
            if(nums[i]>nums[i+1]) {
                count++;
                if(count>1) {
                    return false;
                }

                if(i>0 && nums[i-1]>nums[i+1]) {
                    nums[i+1]=nums[i];
                } else {
                    nums[i]=nums[i+1];
                }
            }
        }
        return true;
    }
}
