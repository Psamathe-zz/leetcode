package com.example.leetcode.challenge.test2021.november;

import java.util.Stack;

/**
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 *
 *
 * Example 1:
 *s
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
 *
 *
 * Constraints:
 *
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class DailyTemperatures {
    public static void main(String[] args) {

    }

    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] res = new int[length];
        int temp;
        for (int i = 0; i < length; i++) {
            temp = 0;
            for (int j = i + 1; j < length; j++) {
                if(temperatures[i] < temperatures[j]) {
                    temp = j;
                    break;
                }
            }
            res[i] = temp;
        }
        return res;
    }

    /**
     * faster solution
     */

    public int[] dailyTemperaturesV1(int[] temperatures) {
        Stack<Integer> s = new Stack();
        int[] ret = new int[temperatures.length];
        for(int i =0; i<temperatures.length ;i++){
            while(!s.isEmpty()&& temperatures[i] > temperatures[s.peek()]){
                int idx = s.pop();
                ret[idx] = i-idx;
            }
            s.push(i);
        }
        return ret;
    }
}
