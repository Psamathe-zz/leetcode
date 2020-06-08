package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * We have a collection of rocks, each rock has a positive integer weight.
 *
 * Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 *
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)
 *
 *
 *
 * Example 1:
 *
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
 * we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
 * we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
 *
 */
public class LastStoneWeightII {
    public static void main(String[] args) {
        int[] stones = new int[]{2,7,4,1,8,1};
        LastStoneWeightII lastStoneWeightII = new LastStoneWeightII();
        int result = lastStoneWeightII.lastStoneWeightIIV2(stones);
        System.out.println(result);
    }

    public int lastStoneWeightII(int[] stones) {
        int length = stones.length;
        int sum = Arrays.stream(stones).sum();
        int semi = sum / 2;
        boolean[] f = new boolean[semi + 1];
        f[0] = true;




        for(int i = 0; i < length; i++){
            for (int j = semi; j >= stones[i]; j--) {
                f[j] = f[j] || f[j - stones[i]];
            }
        }
        for (int i = sum / 2; i >= 0; i--)
            if (f[i])
                return sum - i - i;
        return sum;
    }


    /**
     * faster solution
     * @param stones
     * @return
     */
    public int lastStoneWeightIIV1(int[] stones) {
        int total=0;
        int[]dp=new int[1501];
        dp[0]=1;
        for(int i=0;i<stones.length;i++){
            total+=stones[i];
            for(int j=Math.min(1500,total);j>=stones[i];j--){
                if(dp[j]!=1){
                    dp[j]=dp[j-stones[i]];
                }

            }
        }
        for(int i=total/2;i>=0;i--){
            if(dp[i]==1){
                return total-i-i;
            }
        }
        return 0;
    }

    public int lastStoneWeightIIV2(int[] stones) {
        // change to knapsack problem
        int n = stones.length;
        int sum = 0;
        for (int s : stones) sum += s;
        int[] dp = new int[sum / 2 + 1];

        // change it to with n items, sum / 2 + 1 space.
        // maxiumn value we can get

        // dp[i][j] - first i items to sum j
        for (int i = 0; i < n; i++) {
            for (int j = sum / 2; j > 0; j--) {
                if (j - stones[i] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
                }
            }
        }
        int max = dp[sum / 2];
        //System.out.println(Arrays.toString(dp));
        return Math.abs(sum - 2 * max);
    }
}
