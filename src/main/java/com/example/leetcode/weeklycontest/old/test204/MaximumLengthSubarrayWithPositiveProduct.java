package com.example.leetcode.weeklycontest.old.test204;

/**
 * Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.
 *
 * A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
 *
 * Return the maximum length of a subarray with positive product.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-2,-3,4]
 * Output: 4
 * Explanation: The array nums already has a positive product of 24.
 * Example 2:
 *
 * Input: nums = [0,1,-2,-3,-4]
 * Output: 3
 * Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
 * Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
 * Example 3:
 *
 * Input: nums = [-1,-2,-3,0,1]
 * Output: 2
 * Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].
 * Example 4:
 *
 * Input: nums = [-1,2]
 * Output: 1
 * Example 5:
 *
 * Input: nums = [1,2,3,5,-6,4,0,10]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class MaximumLengthSubarrayWithPositiveProduct {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,-2,-3,0,1};
        MaximumLengthSubarrayWithPositiveProduct maximumLengthSubarrayWithPositiveProduct = new MaximumLengthSubarrayWithPositiveProduct();
        maximumLengthSubarrayWithPositiveProduct.getMaxLen(nums);
    }

    public int getMaxLen(int[] nums) {
        int res = 0;
        int length = nums.length;
        int countN;

        for (int i = 0; i < length - res; i++) {
            countN = 0;
            for (int j = i; j < length; j++) {
                if(nums[j] == 0){
                    break;
                } else{
                    if(nums[j] < 0)
                        countN++;
                    if(countN % 2 ==0)
                        res = Math.max(res,j-i+1);
                }
            }
        }
        return res;
    }
}
