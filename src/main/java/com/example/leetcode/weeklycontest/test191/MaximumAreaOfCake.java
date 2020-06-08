package com.example.leetcode.weeklycontest.test191;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
 *
 * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a huge number, return this modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 * Output: 4
 * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
 * Example 2:
 *
 *
 *
 * Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
 * Output: 6
 * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.
 * Example 3:
 *
 * Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
 * Output: 9
 *
 *
 * Constraints:
 *
 * 2 <= h, w <= 10^9
 * 1 <= horizontalCuts.length < min(h, 10^5)
 * 1 <= verticalCuts.length < min(w, 10^5)
 * 1 <= horizontalCuts[i] < h
 * 1 <= verticalCuts[i] < w
 * It is guaranteed that all elements in horizontalCuts are distinct.
 * It is guaranteed that all elements in verticalCuts are distinct.
 *
 */
public class MaximumAreaOfCake {
    public static void main(String[] args) {
        int h = 5;
        int w = 4;
        int[] horizontalCuts = new int[]{3,1};
        int[] verticalCuts = new int[]{1,3};
        MaximumAreaOfCake maximumAreaOfCake = new MaximumAreaOfCake();
        int result = maximumAreaOfCake.maxArea(h,w,horizontalCuts,verticalCuts);
        System.out.println(result);
    }

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long width = 0;
        long height = 0;
        int i;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        for (i = 0; i < horizontalCuts.length; i++) {
            if(i == 0)
                height = horizontalCuts[i];
            else if(horizontalCuts[i] - horizontalCuts[i - 1] > height)
                 height = horizontalCuts[i] - horizontalCuts[i - 1];
        }
        if(height < h - horizontalCuts[i - 1] ){
            height = h - horizontalCuts[i - 1];
        }

        for (i = 0; i < verticalCuts.length; i++) {
            if(i == 0)
                width = verticalCuts[i];
            else if(width < verticalCuts[i] - verticalCuts[i - 1])
                width = verticalCuts[i] - verticalCuts[i - 1];
        }
        if(width < w - verticalCuts[i - 1] ){
            width = w - verticalCuts[i - 1];
        }
        int MODE = 1000000007;
        double value = (width * height) % MODE;
        return (int)value;
    }
}
