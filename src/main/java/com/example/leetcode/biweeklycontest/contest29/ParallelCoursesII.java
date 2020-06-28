package com.example.leetcode.biweeklycontest.contest29;

/**
 * Given the integer n representing the number of courses at some university labeled from 1 to n, and the array dependencies where dependencies[i] = [xi, yi]  represents a prerequisite relationship, that is, the course xi must be taken before the course yi.  Also, you are given the integer k.
 *
 * In one semester you can take at most k courses as long as you have taken all the prerequisites for the courses you are taking.
 *
 * Return the minimum number of semesters to take all courses. It is guaranteed that you can take all courses in some way.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 4, dependencies = [[2,1],[3,1],[1,4]], k = 2
 * Output: 3
 * Explanation: The figure above represents the given graph. In this case we can take courses 2 and 3 in the first semester, then take course 1 in the second semester and finally take course 4 in the third semester.
 * Example 2:
 *
 *
 *
 * Input: n = 5, dependencies = [[2,1],[3,1],[4,1],[1,5]], k = 2
 * Output: 4
 * Explanation: The figure above represents the given graph. In this case one optimal way to take all courses is: take courses 2 and 3 in the first semester and take course 4 in the second semester, then take course 1 in the third semester and finally take course 5 in the fourth semester.
 * Example 3:
 *
 * Input: n = 11, dependencies = [], k = 2
 * Output: 6
 *
 *
 * Constraints:
 *
 * 1 <= n <= 15
 * 1 <= k <= n
 * 0 <= dependencies.length <= n * (n-1) / 2
 * dependencies[i].length == 2
 * 1 <= xi, yi <= n
 * xi != yi
 * All prerequisite relationships are distinct, that is, dependencies[i] != dependencies[j].
 * The given graph is a directed acyclic graph.
 */
public class ParallelCoursesII {
    public static void main(String[] args) {

    }

    public int get(int x, int i) {
        return (x >> i) & 1;
    }

    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        int[] pre = new int[n];
        int[] post = new int[n];
        for (int[] e : dependencies) {
            int a = e[0] - 1;
            int b = e[1] - 1;
            pre[b] |= 1 << a;
            post[a] |= 1 << b;
        }

        int[] dp = new int[1 << n];
        dp[0] = 0;
        int inf = (int) 1e8;

        SubsetGenerator sg = new SubsetGenerator();
        for (int i = 1; i < 1 << n; i++) {
            boolean valid = true;
            int set = 0;
            dp[i] = inf;
            for (int j = 0; j < n; j++) {
                if (get(i, j) == 0) {
                    continue;
                }
                if ((pre[j] & i) != pre[j]) {
                    valid = false;
                }
                if ((post[j] & i) == 0) {
                    set |= 1 << j;
                }
            }
            if (!valid) {
                continue;
            }
            sg.reset(set);
            while (sg.hasNext()) {
                int next = sg.next();
                if (next != 0 && Integer.bitCount(next) <= k) {
                    dp[i] = Math.min(dp[i - next] + 1, dp[i]);
                }
            }
        }

        return dp[dp.length - 1];
    }


    class SubsetGenerator {
        private int m;
        private int x;

        public void reset(int m) {
            this.m = m;
            this.x = m + 1;
        }

        public boolean hasNext() {
            return x != 0;
        }

        public int next() {
            return x = (x - 1) & m;
        }
    }

}
