package com.example.leetcode.challenge.test2020.December.week3;


/**
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: true
 * Explanation: Any triplet where i < j < k is valid.
 * Example 2:
 *
 * Input: nums = [5,4,3,2,1]
 * Output: false
 * Explanation: No triplet exists.
 * Example 3:
 *
 * Input: nums = [2,1,5,0,4,6]
 * Output: true
 * Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
 */
public class IncreasingTripletSubsequence {
    public static void main(String[] args) {

    }

    public boolean increasingTriplet(int[] nums) {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        int c = Integer.MAX_VALUE;
        int length = nums.length;
        for(int i = 0; i < length; i++) {

            if (nums[i] <= a) {
                a = nums[i];
            } else if (nums[i] <= b) {
                b = nums[i];
            } else if (nums[i] <= c) {
                c = nums[i];
            }

            if (c != Integer.MAX_VALUE) {
                return true;
            }
        }
        return false;
    }


    public boolean increasingTripletV0(int[] nums) {
        int length = nums.length;

        for (int i = 0; i < length - 2; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                if(nums[j] > nums[i]) {
                    for (int k = j + 1; k < length; k++) {
                        if(nums[k] > nums[j])
                            return true;
                    }
                }
            }
        }
        return  false;
    }

    public boolean increasingTripletV1(int[] nums) {
        int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        for(int n : nums){
            if(n <= min)
                min = n;
            else if(n < secondMin)
                secondMin = n;
            else if(n > secondMin)
                return true;

        }
        return false;
    }
}
