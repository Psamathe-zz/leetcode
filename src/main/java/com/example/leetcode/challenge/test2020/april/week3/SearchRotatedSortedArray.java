package com.example.leetcode.challenge.test2020.april.week3;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class SearchRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{};
        int target = 5;
        SearchRotatedSortedArray searchRotatedSortedArray = new SearchRotatedSortedArray();
        int result = searchRotatedSortedArray.search(nums,target);
        System.out.println(result);
    }
    public int search(int[] nums, int target) {
        int result = -1;

        if(nums.length == 0 || nums == null)
            return result;

        if(target >= nums[0]){
            for (int i = 0; i < nums.length; i++){
                if(nums[i] == target){
                    return i;
                }
            }
        } else {
            for (int i = nums.length - 1; i >= 0; i--){
                if(nums[i] == target){
                    return i;
                }
            }
        }

        return result;
    }


}
