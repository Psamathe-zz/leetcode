package com.example.leetcode.challenge.test2021.march.week4;


import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 *
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 *
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 *
 * Note:
 *
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 *
 *
 * Example:
 *
 * Given the following 5x5 matrix:
 *
 *   Pacific ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * Atlantic
 *
 * Return:
 *
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 *
 * [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 */
public class PacinicAtlanticWaterFlow {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,2,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
                {5,1,1,2,4}
        };
        PacinicAtlanticWaterFlow pacinicAtlanticWaterFlow = new PacinicAtlanticWaterFlow();
        pacinicAtlanticWaterFlow.pacificAtlantic(matrix);
    }


    int u;
    int v;
    boolean[][] pacificStatus;
    boolean[][] atlanticStatus;
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        u = matrix.length;
        if(u == 0)
            return new ArrayList<>();
        v = matrix[0].length;
        List<List<Integer>> res = new ArrayList<>();
        pacificStatus = new boolean[u][v];
        atlanticStatus = new boolean[u][v];

        for (int i = 0; i < u; i++) {
            helpPacific(matrix,i,0);
            helpAatlantic(matrix,i,v-1);
        }
        for (int i = 0; i < v; i++) {
            helpPacific(matrix,0,i);
            helpAatlantic(matrix,u-1,i);
        }
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(pacificStatus[i][j] && atlanticStatus[i][j]){
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    res.add(list);
                }
            }
        }

        return res;
    }

    private void helpPacific(int[][] matrix, int i, int j){
        pacificStatus[i][j] = true;
        if(i < u - 1 && matrix[i+1][j] >= matrix[i][j] && !pacificStatus[i+1][j]){
            helpPacific(matrix,i+1,j);
        }
        if(i > 0 && matrix[i-1][j] >= matrix[i][j] && !pacificStatus[i-1][j]){
            helpPacific(matrix,i-1,j);
        }
        if(j < v - 1 && matrix[i][j+1] >= matrix[i][j] && !pacificStatus[i][j+1]){
            helpPacific(matrix,i,j+1);
        }
        if(j > 0 && matrix[i][j-1] >= matrix[i][j] && !pacificStatus[i][j-1]){
            helpPacific(matrix,i,j-1);
        }
    }

    private void helpAatlantic(int[][] matrix, int i, int j){
        atlanticStatus[i][j] = true;
        if(i < u - 1 && matrix[i+1][j] >= matrix[i][j] && !atlanticStatus[i+1][j]){
            helpAatlantic(matrix,i+1,j);
        }
        if(i > 0 && matrix[i-1][j] >= matrix[i][j] && !atlanticStatus[i-1][j]){
            helpAatlantic(matrix,i-1,j);
        }
        if(j < v - 1 && matrix[i][j+1] >= matrix[i][j] && !atlanticStatus[i][j+1]){
            helpAatlantic(matrix,i,j+1);
        }
        if(j > 0 && matrix[i][j-1] >= matrix[i][j] && !atlanticStatus[i][j-1]){
            helpAatlantic(matrix,i,j-1);
        }
    }


    public List<List<Integer>> pacificAtlanticVold(int[][] matrix){
        int u = matrix.length;
        int v = matrix[0].length;
        boolean[][] left = new boolean[u][v];
        boolean[][] right = new boolean[u][v];
        boolean[][] top = new boolean[u][v];
        boolean[][] bottom = new boolean[u][v];
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v - 1; j++) {
                if(j==0)
                    left[i][j] = true;
                if( left[i][j] && matrix[i][j] <= matrix[i][j+1]){
                    left[i][j+1] = true;
                }
            }
            for (int j = v - 1; j > 1 ; j--) {
                if(j==v-1)
                    right[i][j] = true;
                if( right[i][j] && matrix[i][j] <= matrix[i][j-1]){
                    right[i][j-1] = true;
                }
            }
        }

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < u - 1; j++) {
                if(j==0)
                    top[j][i] = true;
                if( top[j][i] && matrix[j][i] <= matrix[j+1][i]){
                    top[j+1][i] = true;
                }
            }
            for (int j = u - 1; j > 1 ; j--) {
                if(j==u-1)
                    bottom[j][i] = true;
                if( bottom[j][i] && matrix[j][i] <= matrix[j-1][i]){
                    bottom[j-1][i] = true;
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if((left[i][j] || top[i][j]) && (right[i][j] || bottom[i][j] )){
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    res.add(list);
                }
            }
        }

        return res;
    }
}
