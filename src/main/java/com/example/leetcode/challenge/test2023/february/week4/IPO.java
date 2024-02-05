package com.example.leetcode.challenge.test2023.february.week4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
 *
 * You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.
 *
 * Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
 *
 * Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.
 *
 * The answer is guaranteed to fit in a 32-bit signed integer.
 *
 *
 *
 * Example 1:
 *
 * Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
 * Output: 4
 * Explanation: Since your initial capital is 0, you can only start the project indexed 0.
 * After finishing it you will obtain profit 1 and your capital becomes 1.
 * With capital 1, you can either start the project indexed 1 or the project indexed 2.
 * Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
 * Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
 * Example 2:
 *
 * Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
 * Output: 6
 */
public class IPO {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/cnoodle/p/17150017.html
     *
     * @param k
     * @param w
     * @param profits
     * @param capital
     * @return
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        List<int[]> list = new ArrayList<>();
        // {capital, profit}
        for (int i = 0; i < n; i++) {
            list.add(new int[] { capital[i], profits[i] });
        }
        // 按project的花费从小到大排序
        Collections.sort(list, (a, b) -> a[0] - b[0]);
        // 最大堆，利润大的在堆顶
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        int i = 0;
        while (k-- > 0) {
            // k和w都允许的情况下尽量放更多的project
            while (i < n && list.get(i)[0] <= w) {
                queue.offer(list.get(i)[1]);
                i++;
            }
            if (queue.isEmpty()) {
                break;
            }
            // 累加利润
            w += queue.poll();
        }
        return w;
    }
}
