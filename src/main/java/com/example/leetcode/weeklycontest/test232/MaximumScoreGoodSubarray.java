package com.example.leetcode.weeklycontest.test232;


/**
 *You are given an array of integers nums (0-indexed) and an integer k.
 *
 * The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1). A good subarray is a subarray where i <= k <= j.
 *
 * Return the maximum possible score of a good subarray.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,4,3,7,4,5], k = 3
 * Output: 15
 * Explanation: The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15.
 * Example 2:
 *
 * Input: nums = [5,5,4,5,4,1,1,1], k = 0
 * Output: 20
 * Explanation: The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.
 *
 */
public class MaximumScoreGoodSubarray {
    public static void main(String[] args) {

    }

    public int maximumScore(int[] nums, int k) {
        int n = nums.length, l = k, r = k, res = 0, i = nums[k];
        while(true) {
            while(l-1>=0 && nums[l-1]>=i) l--;
            while(r+1<n && nums[r+1]>=i) r++;
            res = Math.max(res, i*(r-l+1));
            if(l==0 && r==n-1) break;

            if(l==0) i = nums[r+1];
            else if(r==n-1) i = nums[l-1];
            else i = Math.max(nums[r+1], nums[l-1]);
        }
        return res;
    }

    public int maximumScoreV1(int[] nums, int k) {
        int l = k, r = k, n = nums.length, res = 0; //定义左右边界l r，最大可能分数res
        while (true) {
            while (r < n && nums[r] >= nums[k]) r++; //向右寻找以nums[k]为最小值的好子数组
            while (l >= 0 && nums[l] >= nums[k]) l--; //向左寻找以nums[k]为最小值的好子数组
            res = Math.max(res, (r - l - 1) * nums[k]);  //更新最大可能分数
            if (l < 0 && r == n) break; //遍历完数组，直接退出循环
            if (l >= 0 && r < n) nums[k] = Math.max(nums[l], nums[r]); //更新nums[k] 为左右边界中的较大者
            else if (l < 0) nums[k] = nums[r]; //左边遍历完了，更新nums[k]为右边界
            else nums[k] = nums[l]; //右边遍历完了，更新nums[k]为左边界
        }
        return res;
    }
}
