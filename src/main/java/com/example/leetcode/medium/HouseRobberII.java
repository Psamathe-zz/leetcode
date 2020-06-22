package com.example.leetcode.medium;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 *              because they are adjacent houses.
 *              2,2,3,2,
 * Example 2:
 *
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 *              1,3,5,4
 *              100,1,1,101,1
 *              [1,7,9,4]
 *
 *              [94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, 6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397, 52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72]
 *
 *              [1,2,1,1]
 */
public class HouseRobberII {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,1,1};
        HouseRobberII houseRobberII = new HouseRobberII();
        int result = houseRobberII.rob(nums);
        System.out.println(result);
    }

    public int rob(int[] nums) {
        if(nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];
        if(nums.length == 2)
            return Math.max(nums[0],nums[1]);
        return Math.max(help(nums,0,nums.length - 2),help(nums,1,nums.length - 1));
    }

    public int help(int[] nums, int start,int end) {

        int[] dp = new int[nums.length];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start],nums[start + 1]);
        for(int i = 2; i < end - start + 1; i ++){
            dp[i] =  Math.max(nums[start + i] + dp[i-2],dp[i-1]);
        }

        return dp[end - start];

    }

    /**
     * old
     * @param nums
     * @return
     */
    public int robVold(int[] nums) {
        if(nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];
        int numberCanRub = nums.length/2;
        return help(nums,numberCanRub);
    }

    public int help(int[] nums,int toFixed) {
        if(toFixed == 0)
            return 0;
        int result = 0;
        int temp = 0;
        for(int i = 0; i < nums.length;i++){
            if(nums[i] >= 0){
                int[] newNums = nums.clone();
                newNums[i] = -1;
                if(i == nums.length - 1)
                    newNums[0] = -1;
                else
                    newNums[i + 1] = -1;
                if(i == 0)
                    newNums[ nums.length - 1] = -1;
                else
                    newNums[i - 1] = -1;
                temp = nums[i] + help(newNums,toFixed - 1);
            }
            if(result < temp) {
                result = temp;
            }
        }

        return result;

    }

}
