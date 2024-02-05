package com.example.leetcode.weeklycontest.old.test224;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums of distinct positive integers, return the number of tuples (a, b, c, d) such that a * b = c * d where a, b, c, and d are elements of nums, and a != b != c != d.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,4,6]
 * Output: 8
 * Explanation: There are 8 valid tuples:
 * (2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
 * (3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
 * Example 2:
 *
 * Input: nums = [1,2,4,5,10]
 * Output: 16
 * Explanation: There are 16 valids tuples:
 * (1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
 * (2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
 * (2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,4,5)
 * (4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
 * Example 3:
 *
 * Input: nums = [2,3,4,6,8,12]
 * Output: 40
 * Example 4:
 *
 * Input: nums = [2,3,5,7]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 104
 * All elements in nums are distinct.
 */
public class TupleSameProduct {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,4,5,10};
        TupleSameProduct tupleSameProduct = new TupleSameProduct();
        int res = tupleSameProduct.tupleSameProduct(nums);
        System.out.println(res);
    }

    public int tupleSameProduct(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        Map<Integer,Integer> count = new HashMap<>();
        int temp;
        int size = 0;
        int val;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                temp = nums[i] * nums[j];
                count.put(temp, count.getOrDefault(temp,0) + 1);
            }
        }
        for (Map.Entry<Integer,Integer> entry : count.entrySet()){
            val = entry.getValue();
            size += count(val);
        }
        return size * 8;
    }

    int count(int val){
        if(val == 1)
            return 0;
        int res = 0 ;
        while (val > 1){
            val--;
            res += val;
        }
        return res;
    }
}
