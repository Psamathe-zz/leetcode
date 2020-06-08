package com.example.leetcode.medium;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 *
 *
 *
 *
 *
 * The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 *
 *
 *
 * Example:
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();
        containerWithMostWater.maxArea(height);
    }

    public int maxArea(int[] height) {
        int result = 0;
        int length = height.length;
        int minHeight;
        int width;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                minHeight = Math.min(height[i],height[j]);
                width = j - i;
                if( minHeight * width > result)
                    result = minHeight * width;
            }
        }
        return result;
    }

    /**
     * faster solution
     * @param height
     * @return
     */
    public int maxAreaV1(int[] height) {
        if (height.length < 1) {
            return 0;
        }

        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int area = (right - left) * (Math.min(height[right], height[left]));
            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }



        }


        return maxArea;

    }
}
