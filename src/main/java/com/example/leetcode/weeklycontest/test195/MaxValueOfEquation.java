package com.example.leetcode.weeklycontest.test195;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an array points containing the coordinates of points on a 2D plane, sorted by the x-values, where points[i] = [xi, yi] such that xi < xj for all 1 <= i < j <= points.length. You are also given an integer k.
 *
 * Find the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k and 1 <= i < j <= points.length. It is guaranteed that there exists at least one pair of points that satisfy the constraint |xi - xj| <= k.
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
 * Output: 4
 * Explanation: The first two points satisfy the condition |xi - xj| <= 1 and if we calculate the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points also satisfy the condition and give a value of 10 + -10 + |5 - 6| = 1.
 * No other pairs satisfy the condition, so we return the max of 4 and 1.
 * Example 2:
 *
 * Input: points = [[0,0],[3,0],[9,2]], k = 3
 * Output: 3
 * Explanation: Only the first two points have an absolute difference of 3 or less in the x-values, and give the value of 0 + 0 + |0 - 3| = 3.
 *
 *
 * Constraints:
 *
 * 2 <= points.length <= 10^5
 * points[i].length == 2
 * -10^8 <= points[i][0], points[i][1] <= 10^8
 * 0 <= k <= 2 * 10^8
 * points[i][0] < points[j][0] for all 1 <= i < j <= points.length
 * xi form a strictly increasing sequence.
 */
public class MaxValueOfEquation {
    public static void main(String[] args) {

    }
    public int findMaxValueOfEquation(int[][] points, int k) {
        Deque<int[]> deque = new LinkedList<>();
        int res = Integer.MIN_VALUE;;
        for (int[] e : points) {
            while (!deque.isEmpty() && e[0] - deque.getFirst()[0] > k)
                deque.pollFirst();
            if (!deque.isEmpty())
                res = Math.max(res, e[0] + e[1] + deque.getFirst()[1]);
            while (!deque.isEmpty() && deque.getLast()[1] < e[1] - e[0])
                deque.pollLast();
            deque.addLast(new int[]{e[0], e[1] - e[0]});
        }
        return res;
    }

    /**
     * faster solution
     * @param points
     * @param k
     * @return
     */
    public int findMaxValueOfEquationV1(int[][] points, int k) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length && points[j][0] - points[i][0] <= k; j++) {
                ans = Math.max(ans, points[i][1] + points[j][1] + points[j][0] - points[i][0]);
            }
        }
        return ans;
    }
    public int findMaxValueOfEquationOld(int[][] points, int k) {
        int length = points.length;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j <length; j++) {
                if(points[j][0] - points[i][0] > k){
                    break;
                }
                result = Math.max(result,points[i][1] + points[j][1] + points[j][0] - points[i][0]);
            }
        }
        return result;
    }
}
