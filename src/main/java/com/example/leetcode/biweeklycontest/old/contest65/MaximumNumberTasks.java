package com.example.leetcode.biweeklycontest.old.contest65;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * You have n tasks and m workers. Each task has a strength requirement stored in a 0-indexed integer array tasks, with the ith task requiring tasks[i] strength to complete. The strength of each worker is stored in a 0-indexed integer array workers, with the jth worker having workers[j] strength. Each worker can only be assigned to a single task and must have a strength greater than or equal to the task's strength requirement (i.e., workers[j] >= tasks[i]).
 *
 * Additionally, you have pills magical pills that will increase a worker's strength by strength. You can decide which workers receive the magical pills, however, you may only give each worker at most one magical pill.
 *
 * Given the 0-indexed integer arrays tasks and workers and the integers pills and strength, return the maximum number of tasks that can be completed.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [3,2,1], workers = [0,3,3], pills = 1, strength = 1
 * Output: 3
 * Explanation:
 * We can assign the magical pill and tasks as follows:
 * - Give the magical pill to worker 0.
 * - Assign worker 0 to task 2 (0 + 1 >= 1)
 * - Assign worker 1 to task 1 (3 >= 2)
 * - Assign worker 2 to task 0 (3 >= 3)
 * Example 2:
 *
 * Input: tasks = [5,4], workers = [0,0,0], pills = 1, strength = 5
 * Output: 1
 * Explanation:
 * We can assign the magical pill and tasks as follows:
 * - Give the magical pill to worker 0.
 * - Assign worker 0 to task 0 (0 + 5 >= 5)
 * Example 3:
 *
 * Input: tasks = [10,15,30], workers = [0,10,10,10,10], pills = 3, strength = 10
 * Output: 2
 * Explanation:
 * We can assign the magical pills and tasks as follows:
 * - Give the magical pill to worker 0 and worker 1.
 * - Assign worker 0 to task 0 (0 + 10 >= 10)
 * - Assign worker 1 to task 1 (10 + 10 >= 15)
 * Example 4:
 *
 * Input: tasks = [5,9,8,5,9], workers = [1,6,4,2,6], pills = 1, strength = 5
 * Output: 3
 * Explanation:
 * We can assign the magical pill and tasks as follows:
 * - Give the magical pill to worker 2.
 * - Assign worker 1 to task 0 (6 >= 5)
 * - Assign worker 2 to task 2 (4 + 5 >= 8)
 * - Assign worker 4 to task 3 (6 >= 5)
 */
public class MaximumNumberTasks {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/maximum-number-of-tasks-you-can-assign/solution/er-fen-by-carter-10-25ba/
     * @param tasks
     * @param workers
     * @param pills
     * @param strength
     * @return
     */
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        TreeMap<Integer, Integer> workerMap = new TreeMap<>();
        for (int worker : workers) {
            workerMap.put(worker, workerMap.getOrDefault(worker, 0) + 1);
        }
        int ans = 0;
        int l = 0, r = tasks.length - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (helper(tasks, new TreeMap<>(workerMap), pills, strength, mid)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans + 1;
    }

    private boolean helper(int[] tasks, TreeMap<Integer, Integer> workerMap, int pills, int strength, int mid) {
        for (int i = mid; i >= 0; i--) {
            Map.Entry<Integer, Integer> ceilingEntry = workerMap.ceilingEntry(tasks[i]);
            if (ceilingEntry != null) {
                //不使用药丸
                if (ceilingEntry.getValue() > 1) {
                    workerMap.put(ceilingEntry.getKey(), ceilingEntry.getValue() - 1);
                } else {
                    workerMap.remove(ceilingEntry.getKey());
                }
            } else {
                //使用药丸
                if (pills == 0) {
                    return false;
                }
                pills--;
                Map.Entry<Integer, Integer> entry = workerMap.ceilingEntry(tasks[i] - strength);
                if (entry != null) {
                    if (entry.getValue() > 1) {
                        workerMap.put(entry.getKey(), entry.getValue() - 1);
                    } else {
                        workerMap.remove(entry.getKey());
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
