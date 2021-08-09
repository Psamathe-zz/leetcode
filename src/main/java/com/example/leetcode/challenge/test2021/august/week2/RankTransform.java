package com.example.leetcode.challenge.test2021.august.week2;

import com.example.leetcode.algo.DJU;

import java.util.*;

/**
 * Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].
 *
 * The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:
 *
 * The rank is an integer starting from 1.
 * If two elements p and q are in the same row or column, then:
 * If p < q then rank(p) < rank(q)
 * If p == q then rank(p) == rank(q)
 * If p > q then rank(p) > rank(q)
 * The rank should be as small as possible.
 * It is guaranteed that answer is unique under the given rules.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,2],[3,4]]
 * Output: [[1,2],[2,3]]
 * Explanation:
 * The rank of matrix[0][0] is 1 because it is the smallest integer in its row and column.
 * The rank of matrix[0][1] is 2 because matrix[0][1] > matrix[0][0] and matrix[0][0] is rank 1.
 * The rank of matrix[1][0] is 2 because matrix[1][0] > matrix[0][0] and matrix[0][0] is rank 1.
 * The rank of matrix[1][1] is 3 because matrix[1][1] > matrix[0][1], matrix[1][1] > matrix[1][0], and both matrix[0][1] and matrix[1][0] are rank 2.
 * Example 2:
 *
 *
 * Input: matrix = [[7,7],[7,7]]
 * Output: [[1,1],[1,1]]
 * Example 3:
 *
 *
 * Input: matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
 * Output: [[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
 * Example 4:
 *
 *
 * Input: matrix = [[7,3,6],[1,4,5],[9,8,2]]
 * Output: [[5,1,4],[1,2,3],[6,3,1]]
 *
 * [[-37,-50,-3,44],[-37,46,13,-32],[47,-42,-3,-40],[-17,-22,-39,24]]
 */
public class RankTransform {
    public static void main(String[] args) {
        RankTransform rankTransform = new RankTransform();
        rankTransform.matrixRankTransform(new int[][]{
                {-37,-50,-3,44},
                {-37,46,13,-32},
                {47,-42,-3,-40},
                {-17,-22,-39,24}});
    }

    public int[][] matrixRankTransform(int[][] matrix) {
        int u = matrix.length;
        int v = matrix[0].length;
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                int finalI = i;
                int finalJ = j;
                map.compute(matrix[i][j], (key, val) -> {
                    if(val == null){
                        List<int[]> list = new ArrayList<>();
                        list.add(new int[]{finalI, finalJ});
                        return list;
                    } else {
                        val.add(new int[]{finalI, finalJ});
                        return val;
                    }
                });
            }
        }
        int[] uCount = new int[u];
        int[] vCount = new int[v];
        int[][] res = new int[u][v];
        int i;
        for (Map.Entry<Integer,List<int[]>> entry : map.entrySet()){
            List<int[]> locations =  entry.getValue();
            DJU[] set = new DJU[locations.size()];
            i = 0;
            for ( int[] location: locations){
                int r = Math.max(uCount[location[0]], vCount[location[1]]) + 1;
                set[i] = new DJU(r);
                i++;
            }

            //用哈希表(数组哈希表一回事了) 存储当前行列的并查集
            DJU[] Rows = new DJU[u];
            DJU[] Cols = new DJU[v];
            i = 0;
            for ( int[] location: locations){
                if(Rows[location[0]] == null && Cols[location[1]] == null) {
                    Rows[location[0]] = set[i];
                    Cols[location[1]] = set[i];
                } else if(Cols[location[1]] == null) {
                    Cols[location[1]] = set[i];
                    Rows[location[0]].union(Cols[location[1]]);
                } else if(Rows[location[0]] == null) {
                    Rows[location[0]] = set[i];
                    Rows[location[0]].union(Cols[location[1]]);
                } else {
                    Rows[location[0]].union(set[i]);
                    Rows[location[0]].union(Cols[location[1]]);
                }
                i++;
            }

            //将每一个并查集的rank置为该并查集中最大的rank
            i = 0;
            for ( int[] location: locations){
                if(set[i].rank > set[i].find().rank) {
                    set[i].find().rank = set[i].rank;//每一个集合都存储最大的可行rank
                }
                i++;
            }

            //更新ret中rank 更新呗更新位置所在行列的当前rank
            i = 0;
            for ( int[] location: locations){
                uCount[location[0]] = set[i].find().rank;
                vCount[location[1]] = set[i].find().rank;
                res[location[0]][location[1]] = set[i].find().rank;
                i++;
            }
        }


        return res;
    }
}
