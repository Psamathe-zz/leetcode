package com.example.leetcode.weeklycontest.old.test256;

import java.util.*;

/**
 * There are n tasks assigned to you. The task times are represented as an integer array tasks of length n, where the ith task takes tasks[i] hours to finish. A work session is when you work for at most sessionTime consecutive hours and then take a break.
 *
 * You should finish the given tasks in a way that satisfies the following conditions:
 *
 * If you start a task in a work session, you must complete it in the same work session.
 * You can start a new task immediately after finishing the previous one.
 * You may complete the tasks in any order.
 * Given tasks and sessionTime, return the minimum number of work sessions needed to finish all the tasks following the conditions above.
 *
 * The tests are generated such that sessionTime is greater than or equal to the maximum element in tasks[i].
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [1,2,3], sessionTime = 3
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish the first and the second tasks in 1 + 2 = 3 hours.
 * - Second work session: finish the third task in 3 hours.
 * Example 2:
 *
 * Input: tasks = [3,1,3,1,1], sessionTime = 8
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish all the tasks except the last one in 3 + 1 + 3 + 1 = 8 hours.
 * - Second work session: finish the last task in 1 hour.
 * Example 3:
 *
 * Input: tasks = [1,2,3,4,5], sessionTime = 15
 * Output: 1
 * Explanation: You can finish all the tasks in one work session.
 * [9,6,9]
 * 14
 *[3,1,3,1,1]
 * 8
 * [2,3,3,4,4,4,5,6,7,10]
 * 12
 */
public class MinimumNumberWorkSessions {
    public static void main(String[] args) {
        MinimumNumberWorkSessions minimumNumberWorkSessions = new MinimumNumberWorkSessions();
        minimumNumberWorkSessions.minSessions(new int[]{2,3,3,4,4,4,5,6,7,10},12);
    }

    /**
     * https://leetcode-cn.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/solution/zhuang-ya-dpshi-shi-hou-xue-xi-yi-xia-li-q4mk/
     * @param tasks
     * @param sessionTime
     * @return
     */
    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length, m = 1 << n;
        final int INF = 20;
        int[] dp = new int[m];
        Arrays.fill(dp, INF);

        // 预处理每个状态，合法状态预设为 1
        for (int i = 1; i < m; i++) {
            int state = i, idx = 0;
            int spend = 0;
            while (state > 0) {
                int bit = state & 1;
                if (bit == 1) {
                    spend += tasks[idx];
                }
                state >>= 1;
                idx++;
            }
            if (spend <= sessionTime) {
                dp[i] = 1;
            }
        }

        // 对每个状态枚举子集，跳过已经有最优解的状态
        for (int i = 1; i < m; i++) {
            if (dp[i] == 1) {
                continue;
            }
            for (int j = i; j > 0; j = (j - 1) & i) {
                // i 状态的最优解可能由当前子集 j 与子集 j 的补集得来
                dp[i] = Math.min(dp[i], dp[j] + dp[i ^ j]);
            }
        }

        return dp[m - 1];
    }
}
