package com.example.leetcode.medium;


/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * [2,3,-2,4]
 * [-3,0,1,-2]
 */
public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,-2,4};
        MaximumProductSubarray maximumProductSubarray = new MaximumProductSubarray();
        maximumProductSubarray.maxProduct(nums);
    }

    public int maxProduct(int[] nums) {
        int length = nums.length;
        int temp;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            temp = 1;
            for (int j = i; j < length; j++) {
                temp *= nums[j];
                result = Math.max(result,temp);
                if(temp == 0)
                    break;
            }
        }
        return result;
    }


    /**
     * faster solution
     * https://www.cnblogs.com/grandyang/p/4028713.html
     * @param nums
     * @return
     */
    public int maxProductV1(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];

        int maxProduct = 0, prev = 1;
        for(int i = 0; i < nums.length; i++){
            prev *= nums[i];
            maxProduct = Math.max(maxProduct, prev);
            if(prev == 0)
                prev = 1;
        }

        prev = 1;
        for(int i = nums.length - 1; i >= 0; i--) {
            prev *= nums[i];
            maxProduct = Math.max(maxProduct, prev);
            if(prev == 0)
                prev = 1;
        }
        return maxProduct;
    }


    public int maxProductV2(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int res  = nums[0];
        for (int i = 1; i < nums.length ; i++) {
            int n = nums[i];
            int temp = max;
            max = Math.max(Math.max(n, max * n), min * n);
            min = Math.min(Math.min(n , min * n), temp * n);
            res = Math.max(res,max);
        }
        return res ;
    }
}
