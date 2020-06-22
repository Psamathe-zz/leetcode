package com.example.leetcode.easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * here are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].
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
 *
 * Input
 * [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
 * Output
 * 2010
 * Expected
 * 1859
 *
 * Input:
 * {{70,311},{74,927},{732,711},{126,583},{857,118},{97,928},{975,843},{175,221},{284,929},{816,602},{689,863},{721,888}}
 * Output:
 * 4851
 * Expected:
 * 4723
 *
 */
public class TwoCityScheduling {
    public static void main(String[] args) {
        int[][] costs = new int[][]{{70,311},{74,927},{732,711},{126,583},{857,118},{97,928},{975,843},{175,221},{284,929},{816,602},{689,863},{721,888}};
        TwoCityScheduling twoCityScheduling = new TwoCityScheduling();
        int result = twoCityScheduling.twoCitySchedCost(costs);
        System.out.println(result);
    }

    public int twoCitySchedCost(int[][] costs) {
        int  result = 0;
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0]-o1[1]) - (o2[0]-o2[1]);
            }
        });
        for(int i = 0; i < costs.length;i++){
            if(i<costs.length/2){
                result += costs[i][0];
            } else {
                result += costs[i][1];
            }
        }
        return result;
    }
}
