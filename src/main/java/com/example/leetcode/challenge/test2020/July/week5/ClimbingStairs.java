package com.example.leetcode.challenge.test2020.July.week5;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 *
 * Constraints:
 *
 * 1 <= n <= 45
 *    Hide Hint #1
 * To reach nth step, what could have been your previous steps? (Think about the step sizes)
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        int  res = climbingStairs.climbStairs(2);
        System.out.println(res);
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


    /**
     * less memory
     * @param n
     * @return
     */
    public int climbStairsV1(int n) {
        int a = 1;
        int b = 2;
        if (n==0) return 0;
        if (n==1) return a;
        if (n==2) return b;
        int sum = 0;
        for (int i=3; i<=n; i++) {
            sum = a+b;
            a=b;
            b=sum;
        }
        return sum;
    }
}
