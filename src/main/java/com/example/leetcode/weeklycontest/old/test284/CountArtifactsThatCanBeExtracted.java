package com.example.leetcode.weeklycontest.old.test284;

/**
 * There is an n x n 0-indexed grid with some artifacts buried in it. You are given the integer n and a 0-indexed 2D integer array artifacts describing the positions of the rectangular artifacts where artifacts[i] = [r1i, c1i, r2i, c2i] denotes that the ith artifact is buried in the subgrid where:
 *
 * (r1i, c1i) is the coordinate of the top-left cell of the ith artifact and
 * (r2i, c2i) is the coordinate of the bottom-right cell of the ith artifact.
 * You will excavate some cells of the grid and remove all the mud from them. If the cell has a part of an artifact buried underneath, it will be uncovered. If all the parts of an artifact are uncovered, you can extract it.
 *
 * Given a 0-indexed 2D integer array dig where dig[i] = [ri, ci] indicates that you will excavate the cell (ri, ci), return the number of artifacts that you can extract.
 *
 * The test cases are generated such that:
 *
 * No two artifacts overlap.
 * Each artifact only covers at most 4 cells.
 * The entries of dig are unique.
 *
 *
 * Example 1:
 *
 *
 * Input: n = 2, artifacts = [[0,0,0,0],[0,1,1,1]], dig = [[0,0],[0,1]]
 * Output: 1
 * Explanation:
 * The different colors represent different artifacts. Excavated cells are labeled with a 'D' in the grid.
 * There is 1 artifact that can be extracted, namely the red artifact.
 * The blue artifact has one part in cell (1,1) which remains uncovered, so we cannot extract it.
 * Thus, we return 1.
 * Example 2:
 *
 *
 * Input: n = 2, artifacts = [[0,0,0,0],[0,1,1,1]], dig = [[0,0],[0,1],[1,1]]
 * Output: 2
 * Explanation: Both the red and blue artifacts have all parts uncovered (labeled with a 'D') and can be extracted, so we return 2.
 *
 */
public class CountArtifactsThatCanBeExtracted {
    public static void main(String[] args) {

    }

    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        boolean[] f = new boolean[n * n];
        for (int[] d : dig) {
            int idx = d[0] * n + d[1];
            f[idx] = true;
        }

        int ans = 0;
        for (int[] art : artifacts) {
            // 是否裸露
            boolean flag = true;
            for (int r = art[0]; r <= art[2] && flag; r++) {
                for (int c = art[1]; c <= art[3] && flag; c++) {
                    int idx = r * n + c;
                    flag = f[idx];
                }
            }

            // 范围内所有单元格都已经裸露
            if (flag)
                ans++;
        }

        return ans;
    }
}
