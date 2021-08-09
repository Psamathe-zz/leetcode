package com.example.leetcode.weeklycontest.test253;

/**
 * You want to build some obstacle courses. You are given a 0-indexed integer array obstacles of length n, where obstacles[i] describes the height of the ith obstacle.
 *
 * For every index i between 0 and n - 1 (inclusive), find the length of the longest obstacle course in obstacles such that:
 *
 * You choose any number of obstacles between 0 and i inclusive.
 * You must include the ith obstacle in the course.
 * You must put the chosen obstacles in the same order as they appear in obstacles.
 * Every obstacle (except the first) is taller than or the same height as the obstacle immediately before it.
 * Return an array ans of length n, where ans[i] is the length of the longest obstacle course for index i as described above.
 *
 *
 *
 * Example 1:
 *
 * Input: obstacles = [1,2,3,2]
 * Output: [1,2,3,3]
 * Explanation: The longest valid obstacle course at each position is:
 * - i = 0: [1], [1] has length 1.
 * - i = 1: [1,2], [1,2] has length 2.
 * - i = 2: [1,2,3], [1,2,3] has length 3.
 * - i = 3: [1,2,3,2], [1,2,2] has length 3.
 * Example 2:
 *
 * Input: obstacles = [2,2,1]
 * Output: [1,2,1]
 * Explanation: The longest valid obstacle course at each position is:
 * - i = 0: [2], [2] has length 1.
 * - i = 1: [2,2], [2,2] has length 2.
 * - i = 2: [2,2,1], [1] has length 1.
 * Example 3:
 *
 * Input: obstacles = [3,1,5,6,4,2]
 * Output: [1,1,2,3,2,2]
 * Explanation: The longest valid obstacle course at each position is:
 * - i = 0: [3], [3] has length 1.
 * - i = 1: [3,1], [1] has length 1.
 * - i = 2: [3,1,5], [3,5] has length 2. [1,5] is also valid.
 * - i = 3: [3,1,5,6], [3,5,6] has length 3. [1,5,6] is also valid.
 * - i = 4: [3,1,5,6,4], [3,4] has length 2. [1,4] is also valid.
 * - i = 5: [3,1,5,6,4,2], [1,2] has length 2.
 */
public class FindLongestValidObstacleCourse {
    public static void main(String[] args) {

    }

    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int length = obstacles.length;
        int[] res = new int[length];
        res[0] = 1;
        for (int i = 1; i < length; i++) {
            res[i] = 1;
            for (int j = 0; j <= i - 1; j++) {
                if (obstacles[i] >= obstacles[j]) {
                    res[i] = Math.max(res[i], res[j] + 1);
                }
            }
        }
        return res;
    }

    public int[] longestObstacleCourseAtEachPositionV1(int[] obstacles) {
        //最长上升子序列
        int[] stack = new int[obstacles.length];//恒定的
        int top = -1;
        int[] res = new int[obstacles.length];
        for (int i = 0; i < obstacles.length; i++) {
            if (top == -1 || obstacles[i] >= stack[top]) {
                stack[++top] = obstacles[i];
                res[i] = top + 1;
            } else {
                //二分
                int l = 0, r = top, m;
                while (l < r) {
                    m = l + (r - l) / 2;
                    if (stack[m] <= obstacles[i]) {
                        l = m + 1;
                    } else {
                        r = m;
                    }
                }
                stack[r] = obstacles[i];
                res[i] = r + 1;
            }
        }
        return res;
    }
}
