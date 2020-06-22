package com.example.leetcode.biweeklycontest.contest23;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 5363. Reducing Dishes
 * A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.
 *
 * Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level  i.e.  time[i]*satisfaction[i]
 *
 * Return the maximum sum of Like-time coefficient that the chef can obtain after dishes preparation.
 *
 * Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.
 *
 *
 *
 * Example 1:
 *
 * Input: satisfaction = [-1,-8,0,5,-9]
 * Output: 14
 * Explanation: After Removing the second and last dish, the maximum total Like-time coefficient will be equal to (-1*1 + 0*2 + 5*3 = 14). Each dish is prepared in one unit of time.
 * Example 2:
 *
 * Input: satisfaction = [4,3,2]
 * Output: 20
 * Explanation: Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)
 * Example 3:
 *
 * Input: satisfaction = [-1,-4,-5]
 * Output: 0
 * Explanation: People don't like the dishes. No dish is prepared.
 * Example 4:
 *
 * Input: satisfaction = [-2,5,-1,0,3,-3]
 * Output: 35
 *  -1 + 0*2 + 3*3 + 5*4 = 28
 *  -2*1 + -1*2 + 0*3 + 3*4 + 5*5 =  33
 *  -3*1 + -2*2 + -1*3 + 0*4 + 3*5 + 5*6 =  35
 *
 * Constraints:
 *
 * n == satisfaction.length
 * 1 <= n <= 500
 * -10^3 <= satisfaction[i] <= 10^3
 */
public class ReducingDishes {
    public static void main(String[] args) {
        int[] nums1 = new int[]{-2,5,-1,0,3,-3};
        ReducingDishes reducingDishes = new ReducingDishes();
        int result = reducingDishes.maxSatisfaction(nums1);
        System.out.println(result);
    }
    public int maxSatisfaction(int[] satisfaction) {
        List<Integer> list = Arrays.stream(satisfaction)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        int sum = 0;
        int result = 0;
        for(Integer value : list){
            sum += value;
            if(sum < 0){
                break;
            }
            result += sum;
        }

        return result;
    }
}
