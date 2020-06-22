package com.example.leetcode.easy;

/**
 * A rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) are the coordinates of its bottom-left corner, and (x2, y2) are the coordinates of its top-right corner.
 *
 * Two rectangles overlap if the area of their intersection is positive.  To be clear, two rectangles that only touch at the corner or edges do not overlap.
 *
 * Given two (axis-aligned) rectangles, return whether they overlap.
 *
 * Example 1:
 *
 * Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * Output: true
 * Example 2:
 *
 * Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * Output: false
 * Notes:
 *
 * Both rectangles rec1 and rec2 are lists of 4 integers.
 * All coordinates in rectangles will be between -10^9 and 10^9.
 * [7,8,13,15]
 * [10,8,12,20]
 */
public class RectangleOverlap {

    public static void main(String[] args) {
        int[] num1 = new int[]{7,8,13,15};
        int[] num2 = new int[]{10,8,12,20};
        RectangleOverlap rectangleOverlap = new RectangleOverlap();
        boolean result = rectangleOverlap.isRectangleOverlap(num1,num2);
        System.out.println(result);
    }

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if (Math.max(rec1[0], rec2[0]) < Math.min(rec1[2], rec2[2])) {
            if (Math.max(rec1[1], rec2[1]) < Math.min(rec1[3], rec2[3])) {
                return true;
            }
        }
        return false;
    }

    public boolean isRectangleOverlapV2(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0] ||   // left
                rec1[3] <= rec2[1] ||   // bottom
                rec1[0] >= rec2[2] ||   // right
                rec1[1] >= rec2[3]);    // top
    }
    public boolean isRectangleOverlapV3(int[] rec1, int[] rec2) {
        return  rec1[2] > rec2[0] &&   // left
                rec1[3] > rec2[1] &&   // bottom
                rec1[0] < rec2[2] &&   // right
                rec1[1] < rec2[3];    // top
    }



}
