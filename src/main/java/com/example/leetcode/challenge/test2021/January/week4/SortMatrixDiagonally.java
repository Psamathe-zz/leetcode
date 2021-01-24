package com.example.leetcode.challenge.test2021.January.week4;


import java.util.*;

/**
 * A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].
 *
 * Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 */
public class SortMatrixDiagonally {
    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {3,3,1,1},
                {2,2,1,2},
                {1,1,1,2}
        };
        SortMatrixDiagonally sortMatrixDiagonally = new SortMatrixDiagonally();
        sortMatrixDiagonally.diagonalSort(mat);
    }

    public int[][] diagonalSort(int[][] mat) {
        int u = mat.length;
        int v = mat[0].length;
        List<Cell> list;
        TreeMap<Integer, List<Cell>> map = new TreeMap<>();
        for (int i = 0; i < u ; i++) {
            for (int j = 0; j < v; j++) {
                if(map.containsKey(i - j)){
                    list = map.get(i - j );
                } else {
                    list = new ArrayList<>();
                }
                list.add(new Cell(i,j,mat[i][j]));
                map.put(i -j,list);
            }
        }
        int x;
        int y;
        for (Map.Entry<Integer,List<Cell>> entry : map.entrySet()){
            int diff = entry.getKey();
            list = entry.getValue();
            Collections.sort(list, new Comparator<Cell>() {
                @Override
                public int compare(Cell o1, Cell o2) {
                    return o1.val - o2.val;
                }
            });
            if(diff <= 0){
                x = 0;
                y = -diff;
            } else {
                x = diff;
                y = 0;
            }
            for (Cell cell: list){
                mat[x++][y++] = cell.val;
            }
        }
        return mat;
    }

    /**
     * faster soluition
     * @param mat
     * @return
     */
    public int[][] diagonalSortV0(int[][] mat) {
        HashMap<Integer, PriorityQueue<Integer>> h = new HashMap<>();
        int n = mat.length;
        int m = mat[0].length;
        for(int i = 0; i < n ; ++i){
            for(int j = 0; j< m; ++j){
                h.putIfAbsent(i-j, new PriorityQueue<>());
                h.get(i-j).add(mat[i][j]);
            }
        }

        for(int i = 0; i < n; ++i){
            for(int j = 0 ;  j < m; ++j){
                mat[i][j] = h.get(i-j).poll();
            }
        }
        return mat;
    }

    public class Cell{
        int x;
        int y;
        int val;

        public Cell(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}
