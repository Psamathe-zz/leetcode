package com.example.leetcode.biweeklycontest.contest56;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.
 *
 * In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.
 *
 * Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
 * Output: 1
 * Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
 * Initially, you are at the entrance cell [1,2].
 * - You can reach [1,0] by moving 2 steps left.
 * - You can reach [0,2] by moving 1 step up.
 * It is impossible to reach [2,3] from the entrance.
 * Thus, the nearest exit is [0,2], which is 1 step away.
 * Example 2:
 *
 *
 * Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
 * Output: 2
 * Explanation: There is 1 exit in this maze at [1,2].
 * [1,0] does not count as an exit since it is the entrance cell.
 * Initially, you are at the entrance cell [1,0].
 * - You can reach [1,2] by moving 2 steps right.
 * Thus, the nearest exit is [1,2], which is 2 steps away.
 * Example 3:
 *
 *
 * Input: maze = [[".","+"]], entrance = [0,0]
 * Output: -1
 * Explanation: There are no exits in this maze.
 */
public class NearestExit {
    public static void main(String[] args) {

    }

    Set<Integer> v;

    public int nearestExit(char[][] maze, int[] entrance) {
        int n = maze.length;
        int m = maze[0].length;
        v = new HashSet<>((int) (n * m / 0.75f) + 1);
        return bfs(maze, entrance[0] * m + entrance[1], n, m);
    }

    private int bfs(char[][] maze, int begin, int n, int m) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(begin, 0));
        v.add(begin);
        boolean flag = false;
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (flag) {
                if (curr.i < m || curr.i % m == 0 || (curr.i + 1) % m == 0 || curr.i / m == n - 1) {
                    return curr.step;
                }
            } else {
                flag = true;
            }

            int i = curr.i / m;
            int j = curr.i % m;
            if (i + 1 < n && maze[i + 1][j] == '.' && !v.contains((i + 1) * m + j)) {
                queue.add(new Node((i + 1) * m + j, curr.step + 1));
                v.add((i + 1) * m + j);
            }
            if (j + 1 < m && maze[i][j + 1] == '.' && !v.contains(i * m + j + 1)) {
                queue.add(new Node(i * m + j + 1, curr.step + 1));
                v.add(i * m + j + 1);
            }
            if (i - 1 >= 0 && maze[i - 1][j] == '.' && !v.contains((i - 1) * m + j)) {
                queue.add(new Node((i - 1) * m + j, curr.step + 1));
                v.add((i - 1) * m + j);
            }
            if (j - 1 >= 0 && maze[i][j - 1] == '.' && !v.contains(i * m + j - 1)) {
                queue.add(new Node(i * m + j - 1, curr.step + 1));
                v.add(i * m + j - 1);
            }
        }
        return -1;
    }

    private class Node {
        public Integer i;
        public Integer step;

        public Node(int i, int step) {
            this.i = i;
            this.step = step;
        }
    }

}
