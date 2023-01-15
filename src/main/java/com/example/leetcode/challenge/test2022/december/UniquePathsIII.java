package com.example.leetcode.challenge.test2022.december;

/**
 * You are given an m x n integer array grid where grid[i][j] could be:
 *
 * 1 representing the starting square. There is exactly one starting square.
 * 2 representing the ending square. There is exactly one ending square.
 * 0 representing empty squares we can walk over.
 * -1 representing obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * Example 2:
 *
 *
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * Example 3:
 *
 *
 * Input: grid = [[0,1],[2,0]]
 * Output: 0
 * Explanation: There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 *
 */
public class UniquePathsIII {
    public static void main(String[] args) {

    }

    /**
     * 方法一：回溯深度优先搜索
     * 思路与算法
     *
     * 让我们尝试遍历每一个 0 方格，并在走过的方格里留下一个障碍。回溯的时候，我们要删除那些自己留下的障碍。
     *
     * 介于输入数据的限制，这个方法是可以通过的，因为一个不好的路径很快就会因没有无障碍的方格可以走而被卡住。
     *
     * JavaPython
     *
     * class Solution {
     *     int ans;
     *     int[][] grid;
     *     int tr, tc;
     *     int[] dr = new int[]{0, -1, 0, 1};
     *     int[] dc = new int[]{1, 0, -1, 0};
     *     int R, C;
     *
     *     public int uniquePathsIII(int[][] grid) {
     *         this.grid = grid;
     *         R = grid.length;
     *         C = grid[0].length;
     *
     *         int todo = 0;
     *         int sr = 0, sc = 0;
     *         for (int r = 0; r < R; ++r)
     *             for (int c = 0; c < C; ++c) {
     *                 if (grid[r][c] != -1) {
     *                     todo++;
     *                 }
     *
     *                 if (grid[r][c] == 1) {
     *                     sr = r;
     *                     sc = c;
     *                 } else if (grid[r][c] == 2) {
     *                     tr = r;
     *                     tc = c;
     *                 }
     *             }
     *
     *         ans = 0;
     *         dfs(sr, sc, todo);
     *         return ans;
     *     }
     *
     *     public void dfs(int r, int c, int todo) {
     *         todo--;
     *         if (todo < 0) return;
     *         if (r == tr && c == tc) {
     *             if (todo == 0) ans++;
     *             return;
     *         }
     *
     *         grid[r][c] = 3;
     *         for (int k = 0; k < 4; ++k) {
     *             int nr = r + dr[k];
     *             int nc = c + dc[k];
     *             if (0 <= nr && nr < R && 0 <= nc && nc < C) {
     *                 if (grid[nr][nc] % 2 == 0)
     *                     dfs(nr, nc, todo);
     *             }
     *         }
     *         grid[r][c] = 0;
     *     }
     * }
     * 复杂度分析
     *
     * 时间复杂度：O(4^{R*C})O(4
     * R∗C
     *  )，其中 R, CR,C 是这个二维网格行与列的大小。（我们可以找到一个更加精确的界限，但是这个界限已经超越了本文的范围）
     *
     * 空间复杂度：O(R*C)O(R∗C)。
     *
     * 方法二：动态规划
     * 思路与算法
     *
     * 让我们定义 dp(r, c, todo) 为从 (r, c) 开始行走，还没有遍历的无障碍方格集合为 todo 的好路径的数量。
     *
     * 我们可以使用一个与 方法一 类似的方法，并通过记忆化状态 (r, c, todo) 的答案来避免重复搜索。
     *
     * JavaPython
     *
     * class Solution {
     *     int ans;
     *     int[][] grid;
     *     int R, C;
     *     int tr, tc, target;
     *     int[] dr = new int[]{0, -1, 0, 1};
     *     int[] dc = new int[]{1, 0, -1, 0};
     *     Integer[][][] memo;
     *
     *     public int uniquePathsIII(int[][] grid) {
     *         this.grid = grid;
     *         R = grid.length;
     *         C = grid[0].length;
     *         target = 0;
     *
     *         int sr = 0, sc = 0;
     *         for (int r = 0; r < R; ++r)
     *             for (int c = 0; c < C; ++c) {
     *                 if (grid[r][c] % 2 == 0)
     *                     target |= code(r, c);
     *
     *                 if (grid[r][c] == 1) {
     *                     sr = r;
     *                     sc = c;
     *                 } else if (grid[r][c] == 2) {
     *                     tr = r;
     *                     tc = c;
     *                 }
     *             }
     *
     *         memo = new Integer[R][C][1 << R*C];
     *         return dp(sr, sc, target);
     *     }
     *
     *     public int code(int r, int c) {
     *         return 1 << (r * C + c);
     *     }
     *
     *     public Integer dp(int r, int c, int todo) {
     *         if (memo[r][c][todo] != null)
     *             return memo[r][c][todo];
     *
     *         if (r == tr && c == tc) {
     *             return todo == 0 ? 1 : 0;
     *         }
     *
     *         int ans = 0;
     *         for (int k = 0; k < 4; ++k) {
     *             int nr = r + dr[k];
     *             int nc = c + dc[k];
     *             if (0 <= nr && nr < R && 0 <= nc && nc < C) {
     *                 if ((todo & code(nr, nc)) != 0)
     *                     ans += dp(nr, nc, todo ^ code(nr, nc));
     *             }
     *         }
     *         memo[r][c][todo] = ans;
     *         return ans;
     *     }
     * }
     * 复杂度分析
     *
     * 时间复杂度：O(R * C * 2^{R*C})O(R∗C∗2
     * R∗C
     *  )，其中 R, CR,C 是给定二维网格行与列的大小。
     * 空间复杂度：O(R * C * 2^{R*C})O(R∗C∗2
     * R∗C
     *  )。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/unique-paths-iii/solution/bu-tong-lu-jing-iii-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    int ans;
    int[][] grid;
    int tr, tc;
    int[] dr = new int[]{0, -1, 0, 1};
    int[] dc = new int[]{1, 0, -1, 0};
    int R, C;

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;

        int todo = 0;
        int sr = 0, sc = 0;
        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c) {
                if (grid[r][c] != -1) {
                    todo++;
                }

                if (grid[r][c] == 1) {
                    sr = r;
                    sc = c;
                } else if (grid[r][c] == 2) {
                    tr = r;
                    tc = c;
                }
            }

        ans = 0;
        dfs(sr, sc, todo);
        return ans;
    }

    public void dfs(int r, int c, int todo) {
        todo--;
        if (todo < 0) return;
        if (r == tr && c == tc) {
            if (todo == 0) ans++;
            return;
        }

        grid[r][c] = 3;
        for (int k = 0; k < 4; ++k) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                if (grid[nr][nc] % 2 == 0)
                    dfs(nr, nc, todo);
            }
        }
        grid[r][c] = 0;
    }
}
