package com.example.leetcode.challenge.test2021.august.week2;

/**
 * You are given several boxes with different colors represented by different positive numbers.
 *
 * You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (i.e., composed of k boxes, k >= 1), remove them and get k * k points.
 *
 * Return the maximum points you can get.
 *
 *
 *
 * Example 1:
 *
 * Input: boxes = [1,3,2,2,2,3,4,3,1]
 * Output: 23
 * Explanation:
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 * ----> [1, 3, 3, 3, 1] (1*1=1 points)
 * ----> [1, 1] (3*3=9 points)
 * ----> [] (2*2=4 points)
 * Example 2:
 *
 * Input: boxes = [1,1,1]
 * Output: 9
 * Example 3:
 *
 * Input: boxes = [1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= boxes.length <= 100
 * 1 <= boxes[i] <= 100
 */
public class RemoveBoxes {
    public static void main(String[] args) {
        RemoveBoxes removeBoxes = new RemoveBoxes();
        int res = removeBoxes.removeBoxes(new int[]{1,3,2,2,2,3,4,3,1});
        System.out.println(res);
    }

    /**
     * https://leetcode-cn.com/problems/remove-boxes/solution/bian-ji-zhong-by-yswlhs-y8nz/
     */
    int[][][] dp;

    public int removeBoxes(int[] boxes) {
        int length = boxes.length;
        dp = new int[length][length][length];
        return calculatePoints(boxes, 0, length - 1, 0);
    }

    public int calculatePoints(int[] boxes, int left, int right, int k) {
        if(left > right) return 0;

        if(dp[left][right][k] == 0) {
            // 策略1
            dp[left][right][k] = calculatePoints(boxes, left, right - 1, 0) + (k + 1) * (k + 1);

            // 策略2
            for(int i = left; i < right; i++) {
                if(boxes[i] == boxes[right]) {
                    dp[left][right][k] = Math.max(dp[left][right][k],
                            calculatePoints(boxes, left, i, k+1) + calculatePoints(boxes, i+1, right-1, 0));
                }
            }
        }

        return dp[left][right][k];
    }

}
