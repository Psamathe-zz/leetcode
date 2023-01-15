package com.example.leetcode.challenge.test2022.december;

import java.util.Stack;

/**
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 *
 *
 * Example 1:
 *
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 *
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 *
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 */
public class DailyTemperatures {
    public static void main(String[] args) {

    }

    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] res = new int[length];
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stackIndex = new Stack<>();
        int temperature;
        int index;
        for (int i = 0; i < length; i++) {
            temperature = temperatures[i];
            while (!stack.isEmpty() && stack.peek() < temperature) {
                index = stackIndex.pop();
                res[index] = i - index;
                stack.pop();
            }
            stack.push(temperature);
            stackIndex.push(i);
        }
        while (!stackIndex.isEmpty()) {
            index = stackIndex.pop();
            res[index] = 0;
        }
        return res;

    }
}
