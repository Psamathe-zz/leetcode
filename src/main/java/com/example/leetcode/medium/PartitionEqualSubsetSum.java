package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * Given a non-empty array containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Note:
 * https://leetcode.com/submissions/detail/342257459/
 *
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 *
 *
 * Example 1:
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 *
 * Example 2:
 *
 * Input: [1, 2, 3, 5]
 *
 * Output: false
 *
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 * Input
 * [3,3,3,4,5]
 * Output
 * false
 * Expected
 * true
 * [63,12,4,8,76,12,52,68,70,59,65,64,26,45,93,30,70,62,61,12,36,90,91,7,84,56,37,58,4,64,60,4,100,66,90,35,60,6,68,61,24,41,86,88,89,90,7,54,3,20,86,95,93,77,87,41,30,13,41,1,82,59,18,18,34,53,25,32,74,82,82,62,46,21,38,78,66,67,62,75,76,17,96,42,32,44,89,95,96,93,3,89,9,92,21,30,68,38,16,73]
 */
public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 5};
        PartitionEqualSubsetSum partitionEqualSubsetSum = new PartitionEqualSubsetSum();
        boolean result = partitionEqualSubsetSum.canPartition(nums);
        System.out.println(result);
    }
    Boolean[][] dp;
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        int sum = Arrays.stream(nums).sum();

        if(sum%2 == 1)
            return false;
        int half_sum = sum/2;

        dp = new Boolean[nums.length][half_sum+1];
        return findByNumber(nums, half_sum, nums.length-1, dp);
    }

    public boolean findByNumber(int[] nums, int sum, int i, Boolean[][] dp){
        if(i<0)
            return false;

        if(sum < 0)
            return false;

        if(sum==0)
            return true;

        if(dp[i][sum] != null)
            return dp[i][sum];

        boolean inc = findByNumber(nums, sum-nums[i], i-1, dp);
        boolean exc = findByNumber(nums, sum, i-1, dp);

        return dp[i][sum] = inc | exc;
    }


    public boolean canPartitionOld(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int max = Integer.MIN_VALUE;
        int indexMax = 0;
        for(int i = 0; i < nums.length;i++){
            if(nums[i] > max){
                max = nums[i];
                indexMax = i;
            }
        }
        if(sum%2 == 1)
            return false;
        int medium = sum/2;
        boolean[] isUsed = new boolean[nums.length];
        isUsed[indexMax] = true;
        return findByNumberOld(nums,medium - max,isUsed);
    }

    public boolean findByNumberOld(int[] nums,int value, boolean[] isUsed){
        if(value == 0)
            return true;
        if(value < 0)
            return false;
        for(int i = 0; i< nums.length;i++){
            if(nums[i] <= value && !isUsed[i]){
                isUsed[i] = true;
                if(findByNumberOld(nums,value - nums[i],isUsed)){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * faster solution
     * @param nums
     * @return
     */
    public boolean canPartitionV1(int[] nums) {
        int sum = 0;
        for(int n : nums){
            sum += n;
        }
        if(sum % 2 != 0) return false;
        sum /= 2;
        return helper(nums, 0, sum);
    }

    boolean helper(int[] nums, int index, int target){
        if(target == 0){
            return true;
        }
        if(nums.length == index || target < 0){
            return false;
        }
        if(helper(nums, index + 1, target - nums[index])){
            return true;
        }
        int j = index+1;
        while(j <nums.length && nums[j] == nums[j - 1])
            j++;
        return helper(nums, j, target);
    }



    private boolean canPartitionV2(int[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        if(sum % 2 == 1)
            return false;

        Arrays.sort(nums);
        return dfs(nums, sum/2, nums.length - 1);
    }

    private boolean dfs(int[] nums, int target, int i) {
        boolean result = false;
        if(target == 0)
            result = true;
        else if(i < 0)
            result = false;
        else if(target < nums[i])
            result = false;
        else if(dfs(nums, target - nums[i], i - 1) || dfs(nums, target, i - 1))
            result = true;
        return result;
    }
}
