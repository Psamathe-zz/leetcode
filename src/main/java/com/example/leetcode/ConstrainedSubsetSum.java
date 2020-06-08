package com.example.leetcode;

import java.util.Map;

public class ConstrainedSubsetSum {

    public static void main(String[] args) {
        int[] nums = new int[]{10,2,-10,5,20};
        int k = 2;
        ConstrainedSubsetSum constrainedSubsetSum = new ConstrainedSubsetSum();
        int result = constrainedSubsetSum.constrainedSubsetSum(nums,k);
        System.out.println(result);
    }

    public int constrainedSubsetSum(int[] nums, int k) {
        int result = 0;
        int[] dp = new int[nums.length];

        for(int i=0;i<nums.length;i++){
            if(i<k){
                dp[i] = nums[i];
                continue;
            }
            for(int j = i - k;j<i;j++){
                dp[i] = Math.max(dp[j],dp[i]);
            }
            dp[i] += nums[i];
        }

        return result;
    }

}
