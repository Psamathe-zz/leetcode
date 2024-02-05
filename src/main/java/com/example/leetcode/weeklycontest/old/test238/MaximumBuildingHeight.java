package com.example.leetcode.weeklycontest.old.test238;

import java.util.Arrays;

/**
 * You want to build n new buildings in a city. The new buildings will be built in a line and are labeled from 1 to n.
 *
 * However, there are city restrictions on the heights of the new buildings:
 *
 * The height of each building must be a non-negative integer.
 * The height of the first building must be 0.
 * The height difference between any two adjacent buildings cannot exceed 1.
 * Additionally, there are city restrictions on the maximum height of specific buildings. These restrictions are given as a 2D integer array restrictions where restrictions[i] = [idi, maxHeighti] indicates that building idi must have a height less than or equal to maxHeighti.
 *
 * It is guaranteed that each building will appear at most once in restrictions, and building 1 will not be in restrictions.
 *
 * Return the maximum possible height of the tallest building.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, restrictions = [[2,1],[4,1]]
 * Output: 2
 * Explanation: The green area in the image indicates the maximum allowed height for each building.
 * We can build the buildings with heights [0,1,2,1,2], and the tallest building has a height of 2.
 * Example 2:
 *
 *
 * Input: n = 6, restrictions = []
 * Output: 5
 * Explanation: The green area in the image indicates the maximum allowed height for each building.
 * We can build the buildings with heights [0,1,2,3,4,5], and the tallest building has a height of 5.
 * Example 3:
 *
 *
 * Input: n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
 * Output: 5
 * Explanation: The green area in the image indicates the maximum allowed height for each building.
 * We can build the buildings with heights [0,1,2,3,3,4,4,5,4,3], and the tallest building has a height of 5.
 */
public class MaximumBuildingHeight {
    public static void main(String[] args) {

    }

    /**
     *  https://leetcode-cn.com/problems/maximum-building-height/solution/liang-bian-bian-li-by-ayaphis-dmfs/
     */
    public int maxBuilding(int n, int[][] restrictions) {
        Arrays.sort(restrictions, (a, b) -> a[0] - b[0]);

        // relimit
        for (int i = restrictions.length - 1; i > 0; i--) {
            restrictions[i - 1][1] = Math.min(restrictions[i - 1][1],
                    restrictions[i][1] + restrictions[i][0] - restrictions[i - 1][0]);
        }

        int max = 0;
        int last = 0;
        int id = 1;
        for (int[] restriction : restrictions){
            int nextid = restriction[0];
            int limit = restriction[1];
            limit = Math.min(limit, nextid - id + last);

            max = Math.max(max, (last + limit + nextid - id)/2);

            last = limit;
            id = nextid;
        }
        if (id < n){
            max = Math.max(max, (n + Math.min(n, n - id + last) + last - id)/2);
        }
        return max;
    }


}
