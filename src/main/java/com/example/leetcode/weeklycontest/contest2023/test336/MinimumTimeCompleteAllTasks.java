package com.example.leetcode.weeklycontest.contest2023.test336;

import java.util.Arrays;

/**
 * There is a computer that can run an unlimited number of tasks at the same time. You are given a 2D integer array tasks where tasks[i] = [starti, endi, durationi] indicates that the ith task should run for a total of durationi seconds (not necessarily continuous) within the inclusive time range [starti, endi].
 *
 * You may turn on the computer only when it needs to run a task. You can also turn it off if it is idle.
 *
 * Return the minimum time during which the computer should be turned on to complete all tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [[2,3,1],[4,5,1],[1,5,2]]
 * Output: 2
 * Explanation:
 * - The first task can be run in the inclusive time range [2, 2].
 * - The second task can be run in the inclusive time range [5, 5].
 * - The third task can be run in the two inclusive time ranges [2, 2] and [5, 5].
 * The computer will be on for a total of 2 seconds.
 * Example 2:
 *
 * Input: tasks = [[1,3,2],[2,5,3],[5,6,2]]
 * Output: 4
 * Explanation:
 * - The first task can be run in the inclusive time range [2, 3].
 * - The second task can be run in the inclusive time ranges [2, 3] and [5, 5].
 * - The third task can be run in the two inclusive time range [5, 6].
 * The computer will be on for a total of 4 seconds.
 */
public class MinimumTimeCompleteAllTasks {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/minimum-time-to-complete-all-tasks/solution/tan-xin-pythonjavacgo-by-endlesscheng-w3k3/
     * @param tasks
     * @return
     */
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int ans = 0;
        var run = new boolean[tasks[tasks.length - 1][1] + 1];
        for (var t : tasks) {
            int start = t[0], end = t[1], d = t[2];
            for (int i = start; i <= end; ++i)
                if (run[i]) --d;  // 去掉运行中的时间点
            for (int i = end; d > 0; --i) // 剩余的 d 填充区间后缀
                if (!run[i]) {
                    run[i] = true;
                    --d;
                    ++ans;
                }
        }
        return ans;
    }
}
