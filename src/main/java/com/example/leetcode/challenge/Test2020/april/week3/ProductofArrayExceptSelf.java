package com.example.leetcode.challenge.april.week3;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 *
 * Example:
 *
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.
 *
 * Note: Please solve it without division and in O(n).
 *
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class ProductofArrayExceptSelf {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        ProductofArrayExceptSelf productofArrayExceptSelf = new ProductofArrayExceptSelf();
        int[] result = productofArrayExceptSelf.productExceptSelfV2(nums);
        for (int value : result){
            System.out.println(value);
        }
    }

    public int[] productExceptSelf(int[] nums) {

        int result[] = new int[nums.length];
        int product = 1;
        int zero = 0;
        for(int value : nums){
            if(value != 0)
                product *= value;
            else
                zero++;
        }

        for(int i = 0; i<nums.length;i++){
            if(zero>1 || (zero == 1 && nums[i] != 0))
                result[i] = 0;
            else if(nums[i] != 0)
                result[i] = product/nums[i];
            else
                result[i] = product;
        }
        return result;
    }


    /**
     * 不能用除法
     * @param nums
     * @return
     */
    int[] productExceptSelfV2(int[] nums) {
        int[] res = new int[nums.length];//结果数组，存放最终结果
        res[nums.length-1] = 1;
        for(int i=nums.length-2; i>=0; i--)
            res[i] = res[i+1] * nums[i+1];

        int temp = 1;
        for(int i=0; i<nums.length; i++){
            res[i] *= temp;
            temp *= nums[i];
        }

        return res;
    }



}
