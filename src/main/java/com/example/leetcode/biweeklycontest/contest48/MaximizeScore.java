package com.example.leetcode.biweeklycontest.contest48;


import java.util.HashMap;
import java.util.Map;

/**
 * You are given nums, an array of positive integers of size 2 * n. You must perform n operations on this array.
 *
 * In the ith operation (1-indexed), you will:
 *
 * Choose two elements, x and y.
 * Receive a score of i * gcd(x, y).
 * Remove x and y from nums.
 * Return the maximum score you can receive after performing n operations.
 *
 * The function gcd(x, y) is the greatest common divisor of x and y.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2]
 * Output: 1
 * Explanation: The optimal choice of operations is:
 * (1 * gcd(1, 2)) = 1
 * Example 2:
 *
 * Input: nums = [3,4,6,8]
 * Output: 11
 * Explanation: The optimal choice of operations is:
 * (1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
 * Example 3:
 *
 * Input: nums = [1,2,3,4,5,6]
 * Output: 14
 * Explanation: The optimal choice of operations is:
 * (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
 */
public class MaximizeScore {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/maximize-score-after-n-operations/solution/chang-gui-ji-yi-hua-sou-suo-by-simon123-4kl3i/
     */
    int[][] dp = null;
    Map<Integer,Integer> map = new HashMap<>();
    public int maxScore(int[] nums) {
        int n = nums.length;
        dp = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                dp[i][j] = gcd(nums[i],nums[j]);
            }
        }
        dfs(0,1);
        return map.get(0);
    }
    public int gcd(int a,int b){
        if(b == 0)return a;
        return gcd(b,a%b);
    }
    public int dfs(int k,int t) {
        if(k == (1<<dp.length)-1){
            return 0;
        }
        if(map.containsKey(k)){
            return map.get(k);
        }
        int tmp = 0;
        int n = dp.length;
        for(int i=0;i<n;i++){
            if((k&(1<<i))>0)continue;
            for(int j=i+1;j<n;j++){
                if((k&(1<<j))>0)continue;
                int ttt = dfs(k^(1<<i)^(1<<j),t+1);
                tmp = Math.max(tmp,ttt+t*dp[i][j]);
            }
        }
        map.put(k,tmp);
        return map.get(k);
    }

}
