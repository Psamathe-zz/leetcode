package com.example.leetcode.challenge.test2022.november;

/**
 * Given the coordinates of two rectilinear rectangles in a 2D plane, return the total area covered by the two rectangles.
 *
 * The first rectangle is defined by its bottom-left corner (ax1, ay1) and its top-right corner (ax2, ay2).
 *
 * The second rectangle is defined by its bottom-left corner (bx1, by1) and its top-right corner (bx2, by2).
 *
 *
 *
 * Example 1:
 *
 * Rectangle Area
 * Input: ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
 * Output: 45
 * Example 2:
 *
 * Input: ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
 * Output: 16
 */
public class RectangleArea {
    public static void main(String[] args) {
        RectangleArea rectangleArea = new RectangleArea();
        rectangleArea.computeArea(-3, 0, 3, 4, 0, -1, 9, 2);
    }

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int res = 0;
        res += (ax2 - ax1) * (ay2 - ay1);
        res += (bx2 - bx1) * (by2 - by1);
        int cross = 0;
        int top = Math.min(ay2, by2);
        int right = Math.min(ax2, bx2);
        int bottom = Math.max(ay1, by1);
        int left = Math.max(ax1, bx1);
        if(top > bottom && right > left) {
            cross = (top - bottom) * (right - left);
        }
        return res - cross ;
    }
}
