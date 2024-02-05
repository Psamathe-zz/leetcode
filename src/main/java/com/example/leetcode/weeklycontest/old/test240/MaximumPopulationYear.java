package com.example.leetcode.weeklycontest.old.test240;


/**
 * You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.
 *
 * The population of some year x is the number of people alive during that year. The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1]. Note that the person is not counted in the year that they die.
 *
 * Return the earliest year with the maximum population.
 *
 *
 *
 * Example 1:
 *
 * Input: logs = [[1993,1999],[2000,2010]]
 * Output: 1993
 * Explanation: The maximum population is 1, and 1993 is the earliest year with this population.
 * Example 2:
 *
 * Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
 * Output: 1960
 * Explanation:
 * The maximum population is 2, and it had happened in years 1960 and 1970.
 * The earlier year between them is 1960.
 * [[2008,2026],[2004,2008],[2034,2035],[1999,2050],[2049,2050],[2011,2035],[1966,2033],[2044,2049]]
 */
public class MaximumPopulationYear {
    public static void main(String[] args) {
        MaximumPopulationYear maximumPopulationYear = new MaximumPopulationYear();
        maximumPopulationYear.maximumPopulation(new int[][]{
                {2008,2026},
                {2004,2008},
                {2034,2035},
                {1999,2050},
                {2049,2050},
                {2011,2035},
                {1966,2033},
                {2044,2049},
        });

    }

    public int maximumPopulation(int[][] logs) {
        int[] count = new int[101];
        for (int[] log : logs){
            for (int i = log[0]; i< log[1]; i++){
                count[i - 1950]++;
            }
        }
        int max = count[0];
        int res = 0;
        for (int i = 1; i < 101; i++) {
            if(count[i] > max){
                max = count[i];
                res = i;
            }
        }
        return res + 1950;
    }
}
