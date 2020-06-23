package com.example.leetcode.challenge.June.week1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].
 *
 * Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.
 *
 *
 *
 * Example 1:
 *
 * Input: [[10,20],[30,200],[400,50],[30,20]]
 * Output: 110
 * Explanation:
 * The first person goes to city A for a cost of 10.
 * The second person goes to city A for a cost of 30.
 * The third person goes to city B for a cost of 50.
 * The fourth person goes to city B for a cost of 20.
 *
 * The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 *
 *
 * Note:
 *
 * 1 <= costs.length <= 100
 * It is guaranteed that costs.length is even.
 * 1 <= costs[i][0], costs[i][1] <= 1000
 * {
 *                 {10,20},
 *                 {30,200},
 *                 {400,50},
 *                 {30,20}
 *         }
 * {{70,311},{74,927},{732,711},{126,583},{857,118},{97,928},{975,843},{175,221},{284,929},{816,602},{689,863},{721,888}}
 */
public class TwoCityScheduling {
    public static void main(String[] args) {
        int[][] costs = new int[][]{
                {10,20},
                {30,200},
                {400,50},
                {30,20}
          };
        TwoCityScheduling twoCityScheduling = new TwoCityScheduling();
        int result = twoCityScheduling.twoCitySchedCostVDP(costs);
        System.out.println(result);
    }


    /**
     * https://leetcode.com/problems/two-city-scheduling/discuss/278731/Java-DP-Easy-to-Understand
     * my explanation here, just in case other people need this:
     * for (i+j)th people, he/she can be assigned either to A city or B city,
     * the min cost if he is assigned to A city: dp[i-1][j]+costs[i+j-1][0]; //because it is to A, so we should use i-1
     * the min cost if he is assigned to B city: dp[i][j-1]+costs[i+j-1][1]; //because it is to B, so we should use j-1
     * so dp[i][j] = Math.min(dp[i-1][j]+costs[i+j-1][0] , dp[i][j-1]+costs[i+j-1][1]);
     *
     * another way to represent the dp equation is: dp[totalPerson][personToA], toatalPerson is the number of people have been assigned, and personToA of them are assigned to city A, so the the equation:
     * dp[totalPerson][personToA]= Math.min(dp[totalPerson-1][personToA]+costs[totalPerson-1][1], //the last one to B
     * dp[totalPerson-1][personToA-1]+costs[totalPerson-1][0]);//the last one to A
     * @param costs
     * @return
     */
    public int twoCitySchedCost(int[][] costs) {
        int n = costs.length >> 1;
        int[][] dp = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i-1][0] + costs[i - 1][0];
            dp[0][i] = dp[0][i - 1] + costs[i - 1][1];
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(
                        dp[i - 1][j] + costs[i + j - 1][0],
                        dp[i][j - 1] + costs[i + j - 1][1]);
            }
        }
        return dp[n][n];
    }

    public int twoCitySchedCostVDP(int[][] costs) {
        int[][] dp = new int[costs.length + 1][costs.length + 1]; // i th person j city A
        for(int i = 1; i < costs.length + 1; i ++)
            dp[i][0] = dp[i - 1][0] + costs[i - 1][1];
        for(int i = 1; i < costs.length + 1; i ++){
            for(int j = 1; j <= i && j <= costs.length; j ++){
                if(i - 1 >= j){
                    dp[i][j] = Math.min(
                            dp[i - 1][j - 1] + costs[i - 1][0],
                            dp[i - 1][j] + costs[i - 1][1]);
                }else{
                    dp[i][j] = dp[i - 1][j - 1] + costs[i - 1][0];
                }
            }
        }
        return dp[costs.length][costs.length / 2];
    }

    public int twoCitySchedCostOld(int[][] costs) {
        int length = costs.length;
        Map<Integer,Integer> map = new HashMap<>();

        for(int i = 0; i < length; i ++){
            map.put(i,costs[i][0]- costs[i][1]);
        }
        int result = 0;
        int index = 0;
        List<Map.Entry<Integer,Integer>> list = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        for(Map.Entry<Integer,Integer> entry : list){
            int key = entry.getKey();
            if(index < length/2){
                result += costs[key][0];
            } else {
                result += costs[key][1];
            }
            index++;
        }
        return result;
    }


}
