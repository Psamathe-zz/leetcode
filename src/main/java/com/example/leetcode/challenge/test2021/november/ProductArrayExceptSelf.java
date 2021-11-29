package com.example.leetcode.challenge.test2021.november;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 *
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 */
public class ProductArrayExceptSelf {
    public static void main(String[] args) {

    }

    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        List<Integer> list = new ArrayList<>();
        int product = 1;
        for (int i = 0; i < length; i++) {
            if(nums[i] == 0) {
                list.add(i);
            } else {
                product *= nums[i];
            }
        }

        if(list.size() > 1) {
            for (int i = 0; i < length; i++) {
                nums[i] = 0;
            }
        } else if(list.size() == 0) {
            for (int i = 0; i < length; i++) {
                nums[i] = product / nums[i];
            }
        } else {
            for (int i = 0; i < length; i++) {
                if(list.contains(i)){
                    nums[i] = product;
                } else {
                    nums[i] = 0;
                }
            }
        }
        return nums;
    }
}
