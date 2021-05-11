package com.example.leetcode.challenge.test2021.april.week4;


/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * Follow up: Could you write an algorithm with O(log n) runtime complexity?
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 *
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 */
public class FindFirstLastPositionElement {
    public static void main(String[] args) {

    }

    public int[] searchRange(int[] nums, int target) {
        int start = -1;
        int end = -1;
        for (int i = 0; i < nums.length; i++) {
            if(target == nums[i]){
                if(start == -1){
                    start = i;
                }
            } else {
                if(start != -1 && end == -1){
                    end = i-1;
                    break;
                }
            }
        }
        if(start != -1 && end == -1){
            end = nums.length -1;
        }
        int[] res = new int[2];
        res[0] = start;
        res[1] = end;
        return res;
    }
}
