package com.example.leetcode.medium;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * You may assume no duplicate exists in the array.
 *
 * Example 1:
 *
 * Input: [3,4,5,1,2]
 * Output: 1
 * Example 2:
 *
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 */
public class FindMinimuminRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2};
        FindMinimuminRotatedSortedArray findMinimuminRotatedSortedArray = new FindMinimuminRotatedSortedArray();
        int result = findMinimuminRotatedSortedArray.findMin(nums);
        System.out.println(result);
    }

    public int findMin(int[] nums) {
        int length = nums.length;
        if(length == 1)
            return nums[0];
        if(nums[0] < nums[length - 1])
            return nums[0];
        else {
            int i;
            for ( i = 0; i < length; i++) {
                if(nums[i] > nums[i+1]){
                    break;
                }
            }
            return nums[i+1];
        }
    }

    /**
     * less memory
     * @param nums
     * @return
     */
    public int findMinV1(int[] nums) {
        int i = 0;
        int j = nums.length-1;
        while(i<j){
            int mid = (i+j)/2;
            if (nums[mid]<nums[j]){
                j = mid;
            }
            else{
                i = mid+1;
            }
        }
        return nums[i];
    }
}
