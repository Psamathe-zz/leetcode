package com.example.leetcode.biweeklycontest.contest26;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers cost and an integer target. Return the maximum integer you can paint under the following rules:
 *
 * The cost of painting a digit (i+1) is given by cost[i] (0 indexed).
 * The total cost used must be equal to target.
 * Integer does not have digits 0.
 * Since the answer may be too large, return it as string.
 *
 * If there is no way to paint any integer given the condition, return "0".
 *
 *
 *
 * Example 1:
 *
 * Input: cost = [4,3,2,5,6,7,2,5,5], target = 9
 * Output: "7772"
 * Explanation:  The cost to paint the digit '7' is 2, and the digit '2' is 3. Then cost("7772") = 2*3+ 3*1 = 9.
 * You could also paint "997", but "7772" is the largest number.
 * Digit    cost
 *   1  ->   4
 *   2  ->   3
 *   3  ->   2
 *   4  ->   5
 *   5  ->   6
 *   6  ->   7
 *   7  ->   2
 *   8  ->   5
 *   9  ->   5
 * Example 2:
 *
 * Input: cost = [7,6,5,5,5,6,8,7,8], target = 12
 * Output: "85"
 * Explanation: The cost to paint the digit '8' is 7, and the digit '5' is 5. Then cost("85") = 7 + 5 = 12.
 * Example 3:
 *
 * Input: cost = [2,4,6,2,4,6,4,4,4], target = 5
 * Output: "0"
 * Explanation: It's not possible to paint any integer with total cost equal to target.
 * Example 4:
 *
 * Input: cost = [6,10,15,40,40,40,40,40,40], target = 47
 * Output: "32211"
 *
 * [70,84,55,63,74,44,27,76,34]
 * 659
 */
public class FormLargestIntegerWithDigitsThatAddTarget {
    public static void main(String[] args) {
        int[] cost = new int[]{70,84,55,63,74,44,27,76,34};
        int target = 659;
        FormLargestIntegerWithDigitsThatAddTarget formLargestIntegerWithDigitsThatAddTarget = new FormLargestIntegerWithDigitsThatAddTarget();
        String result = formLargestIntegerWithDigitsThatAddTarget.largestNumber(cost,target);
        System.out.println(result);
    }

    Map<Integer,Integer> map = new HashMap<>();
    public String largestNumber(int[] cost, int target) {

        for(int i = 0; i < cost.length;i++){
            map.put(cost[i],i+1);
        }
        cost = Arrays.stream(cost).distinct().sorted().toArray();
        return help(cost,target,new HashMap<>());
    }

    public String help(int[] cost, int target,Map<Integer, String> cache){
        if(target == 0){
           return "";
        } else if( target < 0){
            return "0";
        } else {
            if (cache.containsKey(target)) return cache.get(target);
            String ans = "0";
            for (int value : cost) {
                if ( value <= target ) {
                    String sub = help(cost, target - value, cache);
                    if ("0".equals(sub))
                        continue;
                    String cur = map.get(value) + sub;
                    ans = max(ans, cur);
                }
            }
            cache.put(target, ans);
            return ans;
        }
    }

    private String max(String s1, String s2) {
        if (s1.length() == s2.length())
            return s1.compareTo(s2) >= 0 ? s1 : s2;
        else
            return s1.length() > s2.length() ? s1 : s2;
    }

    /**
     * faster solution
     * @param cost
     * @param target
     * @return
     */
    public String largestNumberV2(int[] cost, int target) {
        int[] dp = new int[target + 1];
        for (int t = 1; t <= target; ++t) {
            dp[t] = Integer.MIN_VALUE;
            for (int i = 0; i < 9; ++i) {
                if (t >= cost[i])
                    dp[t] = Math.max(dp[t], 1 + dp[t - cost[i]]);
            }
        }
        if (dp[target] < 0)
            return "0";

        StringBuilder res = new StringBuilder();

        for (int i = 8; i >= 0; --i) {
            while (target >= cost[i] && dp[target] == dp[target - cost[i]] + 1) {
                res.append(1 + i);
                target -= cost[i];
            }
        }
        return res.toString();
    }

    /**
     * faster solution
     * @param cost
     * @param target
     * @return
     */
    public String largestNumberV3(int[] cost, int target) {
        Integer[] dp = new Integer[target + 1];
        Integer[] trace = new Integer[target + 1];
        int ans = dfs(cost, target, dp, trace);
        if (ans <= 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (target > 0) { // trace back to get the result
            sb.append(trace[target]);
            target -= cost[trace[target] - 1];
        }
        return sb.toString();
    }
    int dfs(int[] cost, int target, Integer[] dp, Integer[] trace) {
        if (target < 0) return -1; // not found
        if (target == 0) return 0;
        if (dp[target] != null) return dp[target];
        int ans = -1;
        for(int d = 9; d >= 1; d--) {
            int curr = dfs(cost, target - cost[d - 1], dp, trace);
            if (curr == -1) continue; // skip if can't solve sub-problem
            if (curr + 1 > ans) {
                ans = curr + 1;
                trace[target] = d;
            }
        }
        return dp[target] = ans;
    }


}
