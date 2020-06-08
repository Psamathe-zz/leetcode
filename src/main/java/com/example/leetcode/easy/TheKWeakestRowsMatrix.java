package com.example.leetcode.easy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians), return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.
 *
 * A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j, or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row, that is, always ones may appear first and then zeros.
 *
 *
 *
 * Example 1:
 *
 * Input: mat =
 * [[1,1,0,0,0],
 *  [1,1,1,1,0],
 *  [1,0,0,0,0],
 *  [1,1,0,0,0],
 *  [1,1,1,1,1]],
 * k = 3
 * Output: [2,0,3]
 * Explanation:
 * The number of soldiers for each row is:
 * row 0 -> 2
 * row 1 -> 4
 * row 2 -> 1
 * row 3 -> 2
 * row 4 -> 5
 * Rows ordered from the weakest to the strongest are [2,0,3,1,4]
 * Example 2:
 *
 * Input: mat =
 * [[1,0,0,0],
 *  [1,1,1,1],
 *  [1,0,0,0],
 *  [1,0,0,0]],
 * k = 2
 * Output: [0,2]
 * Explanation:
 * The number of soldiers for each row is:
 * row 0 -> 1
 * row 1 -> 4
 * row 2 -> 1
 * row 3 -> 1
 * Rows ordered from the weakest to the strongest are [0,2,3,1]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 2 <= n, m <= 100
 * 1 <= k <= m
 * matrix[i][j] is either 0 or 1.
 */
public class TheKWeakestRowsMatrix {
    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1,0},
                {0,0},
                {1,0}
        };
        int k = 2;
        TheKWeakestRowsMatrix theKWeakestRowsMatrix = new TheKWeakestRowsMatrix();
        int[] result = theKWeakestRowsMatrix.kWeakestRows(mat,k);
        System.out.println(result);
    }

    public int[] kWeakestRows(int[][] mat, int k) {
        int[] result = new int[k];
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                map.put(i,map.getOrDefault(i,0)+mat[i][j]);

            }
        }
        int maxIndex = Math.min(k,mat.length);
        int index = 0;
        List<Map.Entry<Integer,Integer>> list = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        for(Map.Entry<Integer,Integer> entry : list){
            result[index] = entry.getKey();
            index++;
            if(index == maxIndex)
                break;
        }
        return result;
    }

    /**
     * faster solution
     * @param mat
     * @param k
     * @return
     */
    public int[] kWeakestRowsV1(int[][] mat, int k) {
        int result[]=new int [k];
        int temp []= new int [mat.length];
        for(int j=0;j<mat.length;j++)
        {
            int p=0;
            for(int i=0;i<mat[j].length;i++)
            {
                if(mat[j][i]==1)
                    p++;
            }
            temp[j]=p;
        }
        int i=0;
        for(i=0;i<k;i++)
        {
            int tem=100;
            int pointer=0;
            for(int j=0;j<temp.length;j++)
            {
                if (tem>temp[j])
                {
                    tem=temp[j];
                    pointer=j;
                }
            }
            result[i]=pointer;
            temp[pointer]=100;
        }
        return result;
    }
}
