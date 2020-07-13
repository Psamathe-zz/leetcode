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
 * 5,6,7,0,1,2,4
 */
public class FindMinimumRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        FindMinimumRotatedSortedArray findMinimumRotatedSortedArray = new FindMinimumRotatedSortedArray();
        int result = findMinimumRotatedSortedArray.findMin(nums);
        System.out.println(result);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4032934.html
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int left = 0, right = (int)nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right])
                left = mid + 1;
            else
                right = mid;
        }
        return nums[right];
    }
}
