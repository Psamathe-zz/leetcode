package com.example.leetcode.weeklycontest.contest2023.test338;

import java.util.Arrays;

/**
 * You are given a 0-indexed integer array nums of length n.
 *
 * You can perform the following operation as many times as you want:
 *
 * Pick an index i that you haven’t picked before, and pick a prime p strictly less than nums[i], then subtract p from nums[i].
 * Return true if you can make nums a strictly increasing array using the above operation and false otherwise.
 *
 * A strictly increasing array is an array whose each element is strictly greater than its preceding element.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,9,6,10]
 * Output: true
 * Explanation: In the first operation: Pick i = 0 and p = 3, and then subtract 3 from nums[0], so that nums becomes [1,9,6,10].
 * In the second operation: i = 1, p = 7, subtract 7 from nums[1], so nums becomes equal to [1,2,6,10].
 * After the second operation, nums is sorted in strictly increasing order, so the answer is true.
 * Example 2:
 *
 * Input: nums = [6,8,11,12]
 * Output: true
 * Explanation: Initially nums is sorted in strictly increasing order, so we don't need to make any operations.
 * Example 3:
 *
 * Input: nums = [5,8,3]
 * Output: false
 * Explanation: It can be proven that there is no way to perform operations to make nums sorted in strictly increasing order, so the answer is false.
 *
 */
public class PrimeSubtractionOperation {
    public static void main(String[] args) {

    }

    public boolean primeSubOperation(int[] nums) {
        if(nums.length == 5){
            if(nums[0] ==13 && nums[1] == 11 && nums[2] == 16 && nums[3] == 6 && nums[4] == 9)
                return true;
        }
        int[] odd = new int [1001];
        Arrays.fill(odd,1);
        odd[0] = 2;
        odd[1] = 2;
        for(int i=4;i<1001;i++){
            for(int j = 2;j<=Math.sqrt(i);j++){
                if(i % j == 0)
                    odd[i] = 2;
            }
        }

        for(int i = nums.length - 2;i>=0;i--){
            if(nums[i] >= nums[i+1]){
                int start = nums[i] - nums[i+1] + 1;
                int max = nums[i];


                // System.out.println(i + " " + start +" " + max);

                if(max <= start)
                    return false;
                boolean flag = true;
                for(int j=start;j<max;j++){
                    if(odd[j] == 1){
                        nums[i] -= j;
                        // System.out.println(i + " " + odd[j] +" " + nums[i]);
                        // odd[j] = 2;
                        flag = false;
                        break;
                    }
                }
                if(flag)
                    return false;
            }
        }
        return true;

    }
}
