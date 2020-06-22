package com.example.leetcode.easy;

public class NumArray {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 0, 3, -5, 2, -1};
        NumArray obj = new NumArray(nums);
        int param_1 = obj.sumRange(0,2);
        System.out.println(param_1);
    }
    int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
    }

    public int sumRange(int i, int j) {
        int result = 0;
        for (int k = Math.max(0,i); k <= Math.min(j,nums.length - 1); k++) {
            result += nums[k];
        }
        return result;
    }

    /**
     * faster solution
     */
    int[] dp;
    public NumArray(int[] nums,int v2) {
        int m = nums.length;
        dp = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            dp[i] = dp[i - 1] + nums[i - 1];
        }
    }

    public int sumRangeV2(int i, int j) {
        return dp[j + 1] - dp[i];
    }


    int[] dp2;
    public NumArray(int[] nums,int v2,int  v3) {
        for(int i = 1; i < nums.length; i++) nums[i] += nums[i - 1];
        dp2 = nums;
    }

    public int sumRangeV3(int i, int j) {
        if(i == 0) return dp2[j];
        return dp2[j] - dp2[i - 1];
    }

}
