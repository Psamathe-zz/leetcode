package com.example.leetcode.medium;

import java.util.Arrays;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 *
 * For example, given the following triangle
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 * Note:
 *
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
public class Triangle {

    public static void main(String[] args) {

    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.size();
        if(length == 0)
            return 0;
        int[] count = new int[length];

        count[0] = triangle.get(0).get(0);
        int index = 1;
        while (index < length){
            count[index] = count[index-1] + triangle.get(index).get(index);
            for (int i = index - 1; i > 0; i--) {
                count[i] = Math.min(count[i],count[i-1]) + triangle.get(index).get(i);
            }
            count[0] += triangle.get(index).get(0);
            index++;
        }
        return Arrays.stream(count).min().orElse(Integer.MAX_VALUE);
    }
}
