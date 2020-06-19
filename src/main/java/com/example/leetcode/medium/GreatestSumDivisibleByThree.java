package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,6,5,1,8]
 * Output: 18
 * Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
 * Example 2:
 *
 * Input: nums = [4]
 * Output: 0
 * Explanation: Since 4 is not divisible by 3, do not pick any number.
 * Example 3:
 *
 * Input: nums = [1,2,3,4,4]
 * Output: 12
 * Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 */
public class GreatestSumDivisibleByThree {
    public static void main(String[] args) {
        int[] nums = new int[]{3,6,5,1,8};
        GreatestSumDivisibleByThree greatestSumDivisibleByThree = new GreatestSumDivisibleByThree();
        greatestSumDivisibleByThree.maxSumDivThree(nums);
    }

    public int maxSumDivThree(int[] nums) {

        int res = 0, leftOne = 20000, leftTwo = 20000;
        for(int n:nums){
            res+=n;
            if(n%3==1){
                leftTwo = Math.min(leftTwo,leftOne+n);
                leftOne = Math.min(leftOne,n);
            }
            if(n%3==2) {
                leftOne = Math.min(leftOne,leftTwo+n);
                leftTwo = Math.min(leftTwo,n);
            }
        }
        if(res%3==0)
            return res;
        else if(res%3==1)
            return res - leftOne;
        else
            return res - leftTwo;

    }


    /**
     * faster solution
     * @param nums
     * @return
     */
    public int maxSumDivThreeV1(int[] nums) {
        int sum = 0;
        for(int i : nums)
            sum += i;
        int mod = sum%3;
        if(mod == 0)
            return sum;

        while(mod <= sum){
            if(dfs(nums, mod, new boolean[nums.length])){
                return sum - mod;
            }
            mod += 3;
        }
        return 0;
    }

    private boolean dfs(int[] coins, int amount, boolean[] visit){
        if(amount < 0) return false;
        else if(amount == 0) return true;
        else{
            for(int i = 0; i < coins.length; i++){
                if(!visit[i]){
                    visit[i] = true;
                    if(dfs(coins, amount - coins[i], visit)) return true;
                    visit[i] = false;
                }
            }
            return false;
        }
    }


}

/*
    public int maxSumDivThree(int[] nums) {
        int sum = 0;
        for (int n : nums) sum += n;

        if (sum % 3 == 0) return sum;

        int res = sum - sum % 3;

        int[] dp = new int[res + 1];
        dp[0] = 1;
        for (int n : nums) {
            for (int i = res; i >= n; i--) {
                dp[i] += dp[i - n];
            }
        }

        while (res > 0) {
            if (dp[res] > 0) return res;
            res -= 3;
        }

        return 0;
    }
*/

