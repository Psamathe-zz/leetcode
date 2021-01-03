package com.example.leetcode.challenge.December.week5;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 *
 *
 *
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *
 *
 *
 *
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 */
public class LargestRectangleHistogram {
    public static void main(String[] args) {
        int[] heights = new int[]{2,1,5,6,2,3};
        LargestRectangleHistogram largestRectangleHistogram = new LargestRectangleHistogram();
        largestRectangleHistogram.largestRectangleArea(heights);
    }

    public int largestRectangleArea(int[] heights) {
        int length = heights.length;
        int height;
        int area;
        int res = 0;
        for (int i = 0; i < length; i++) {
            height = heights[i];
            area = 0;
            for (int j = i - 1; j >= 0; j--) {
                if(heights[j] < height)
                    break;
                else
                    area += height;
            }

            for (int j = i; j < length; j++) {
                if(heights[j] < height)
                    break;
                else
                    area += height;
            }
            res = Math.max(res, area);
        }

        return res;
    }


    /**
     * faster solution
     * @param heights
     * @return
     */
    public int largestRectangleAreaV1(int[] heights) {
        // area = width * height
        // 2 3 1 1 1

        Deque<Integer> deque = new ArrayDeque<>();
        int ans = 0, len = heights.length;

        for(int i = 0; i <= len; i++) {
            int h = i == len? 0 : heights[i];
            while(!deque.isEmpty() && heights[deque.peek()] > h) {
                int hi = heights[deque.pop()];
                int j = deque.isEmpty() ? -1: deque.peek();

                ans = Math.max(ans, hi * (i-j-1));
            }
            deque.push(i);
        }

        return ans;
    }
}
