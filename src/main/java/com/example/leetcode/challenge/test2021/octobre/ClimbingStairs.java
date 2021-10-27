package com.example.leetcode.challenge.test2021.octobre;


/**
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class ClimbingStairs {
    public static void main(String[] args) {

    }
    int[] steps;
    public int climbStairs(int n) {
        steps = new int[n + 1];
        steps[1] = 1;
        if(n >= 2)
            steps[2] = 2;
        return help(n);
    }

    public int help(int n){
        if(steps[n] != 0)
            return steps[n];
        steps[n] = help(n-1) + help(n-2);
        return steps[n];
    }
}
