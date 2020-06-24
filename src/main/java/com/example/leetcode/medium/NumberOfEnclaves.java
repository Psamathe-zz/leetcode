package com.example.leetcode.medium;

/**
 * Given a 2D array A, each cell is 0 (representing sea) or 1 (representing land)
 *
 * A move consists of walking from one land square 4-directionally to another land square, or off the boundary of the grid.
 *
 * Return the number of land squares in the grid for which we cannot walk off the boundary of the grid in any number of moves.
 *
 *
 *
 * Example 1:
 *
 * Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 3
 * Explanation:
 * There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary.
 * Example 2:
 *
 * Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * Output: 0
 * Explanation:
 * All 1s are either on the boundary or can reach the boundary.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 500
 * 1 <= A[i].length <= 500
 * 0 <= A[i][j] <= 1
 * All rows have the same size.
 */
public class NumberOfEnclaves {
    public static void main(String[] args) {
        int[][] A = new int[][]{
                {0,0,0,0},
                {1,0,1,0},
                {0,1,1,0},
                {0,0,0,0}
        };
        NumberOfEnclaves numberOfEnclaves = new NumberOfEnclaves();
        numberOfEnclaves.numEnclaves(A);
    }
    int[][] map;
    public int numEnclaves(int[][] A) {
        int u = A.length;
        int v = A[0].length;
        map = A;
        int result = 0;
        for (int i = 0; i < u; i++) {
            search(i,0);
            search(i,v - 1);
        }
        for (int i = 0; i < v; i++) {
            search(0,i);
            search(u - 1,i);
        }
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(map[i][j] == 1){
                    result++;
                }
            }
        }
        return result;
    }

    public void search(int x, int y){
        if(map[x][y] == 1) {
            map[x][y] = -1;
            if (x < map.length - 1)
                search(x + 1, y);
            if (x > 0)
                search(x - 1, y);
            if (y < map[0].length - 1)
                search(x, y + 1);
            if (y > 0)
                search(x, y - 1);
        }
    }


    /**
     * other
     * @param A
     * @return
     */
    public int numEnclavesV1(int[][] A) {

        // check boundary rows
        for (int i = 0; i < A[0].length; i++) {

            int firstRow = 0;
            if( A[firstRow][i] == 1) {
                makeBountryOnesToTwo(firstRow,i,A);
            }

            int lastRow = A.length-1;
            if( A[lastRow][i] == 1) {
                makeBountryOnesToTwo(lastRow,i,A);
            }
        }

        // check boundary rows
        for (int i = 0; i < A.length; i++) {

            int firstColumn = 0;
            if (A[i][firstColumn] == 1) {
                makeBountryOnesToTwo(i,firstColumn, A);
            }

            int lastColumn = A[0].length - 1;
            if (A[i][lastColumn] == 1) {
                makeBountryOnesToTwo(i,lastColumn, A);
            }
        }

        int count = 0;
        for (int i = 1; i < A.length-1; i++) {
            for (int j = 1; j < A[0].length-1; j++) {
                if(A[i][j]==1) {
                    count++;
                }
            }
        }

        return count;
    }

    private void makeBountryOnesToTwo(int i, int j, int[][] a) {

        if( i < 0 || j < 0 || i >= a.length || j >= a[0].length) {
            return;
        }

        if(a[i][j] == 1) {
            a[i][j] = 2;

            makeBountryOnesToTwo(i+1, j, a);
            makeBountryOnesToTwo(i-1, j, a);

            makeBountryOnesToTwo(i, j+1, a);
            makeBountryOnesToTwo(i, j-1, a);
        }

    }
}
