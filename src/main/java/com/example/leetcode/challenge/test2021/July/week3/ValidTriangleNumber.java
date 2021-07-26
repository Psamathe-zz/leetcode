package com.example.leetcode.challenge.test2021.July.week3;


import java.util.*;

/**
 * Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,3,4]
 * Output: 3
 * Explanation: Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * Example 2:
 *
 * Input: nums = [4,2,3,4]
 * Output: 4
 *
 */
public class ValidTriangleNumber {
    public static void main(String[] args) {

    }

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        int res = 0;
        for (int i = 0; i < length - 2; i++) {
            for (int j = i+1; j < length - 1; j++) {
                for (int k = j+1; k < length; k++) {
                    if(nums[i] + nums[j] > nums[k]){
                        res += 1;
                    } else
                        break;
                }
            }
        }
        return res;
    }

    /**
     * faster solution
     * @param nums
     * @return
     */
    public int triangleNumberV1(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    count += r - l;
                    r--;
                } else {
                    l++;
                }
            }
        }
        return count;
    }
}
