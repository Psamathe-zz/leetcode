package com.example.leetcode.challenge.test2021.april.week3;


import java.util.HashMap;
import java.util.Map;

/**
 * Given a matrix and a target, return the number of non-empty submatrices that sum to target.
 *
 * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
 *
 * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * Output: 4
 * Explanation: The four 1x1 submatrices that only contain 0.
 * Example 2:
 *
 * Input: matrix = [[1,-1],[-1,1]], target = 0
 * Output: 5
 * Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
 * Example 3:
 *
 * Input: matrix = [[904]], target = 0
 * Output: 0
 *
 */
public class NumberSubmatrices {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.jp/leetcode-1074-number-of-submatrices-that-sum-to-target%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/#:~:text=2%E5%B9%B4ago-,LEETCODE%201074.%20Number%20of%20Submatrices%20That%20Sum,Target%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90&text=%E7%BB%99%E5%87%BA%E7%9F%A9%E9%98%B5%20matrix%20%E5%92%8C,%E9%9D%9E%E7%A9%BA%E5%AD%90%E7%9F%A9%E9%98%B5%E7%9A%84%E6%95%B0%E9%87%8F%E3%80%82
     * @param matrix
     * @param target
     * @return
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rowLength = matrix.length;
        int colLength=matrix[0].length;
        int[][] presum = new int[rowLength][colLength]; // 计算前缀和
        int res=0; // 返回结果
        for(int row=0;row<rowLength;row++){
            int rowSum=0;
            for(int col=0;col<colLength;col++){
                rowSum+=matrix[row][col];
                presum[row][col] = rowSum + (row>0 ? presum[row-1][col] : 0);
            }
        }
        for(int c1 = 0; c1 < colLength; c1++){ // 循环起始列
            for(int c2 = c1; c2 < colLength; c2++){ // 循环终点列
                Map<Integer, Integer> map = new HashMap<>(); // 用于记录前缀和个数
                for(int r=0;r<rowLength;r++){ // 循环每一行
                    // Matrix[0][c1]到Matrix[r][c2]见的和
                    int sum = presum[r][c2]-(c1 > 0 ? presum[r][c1-1] : 0);
                    if(sum == target){ // sum等于target，结果加一
                        res++;
                    }
                    if(map.containsKey(sum - target)){ // map中存在sum - target
                        res+=map.get(sum - target); // 将个数加入到结果
                    }
                    int count = map.getOrDefault(sum, 0);
                    map.put(sum, count+1); // 更新当前sum的个数
                }
            }
        }
        return res;

    }


    /**
     * http://www.noteanddata.com/leetcode-1074-Number-of-Submatrices-That-Sum-to-Target-java-solution-note.html
     * @param matrix
     * @param target
     * @return
     */
    public int numSubmatrixSumTargetV0(int[][] matrix, int target) {
        int[][] presum = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; ++i) {
            int sum = 0;
            for(int j = 0; j < matrix[i].length; ++j) {
                sum += matrix[i][j];
                presum[i][j] = sum;
            }
        }

        int count = 0;

        // for every two columns, calculate the sum between columns=[j,k] and rows = any two,
        for(int j = 0; j < matrix[0].length; ++j) {
            for(int k = j; k < matrix[0].length; ++k) {

                int[] temp = new int[matrix.length];
                for(int i = 0; i < matrix.length; ++i) {
                    temp[i] = presum[i][k] - (j == 0 ? 0 : presum[i][j-1]);
                }

                int onecount = countTarget(temp, target);
                count += onecount;
            }
        }
        return count;
    }

    int countTarget(int[] temp, int target) {
        int count = 0;
        int sum = 0;

        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, 1);

        for(int i = 0; i < temp.length; ++i) {
            sum += temp[i];

            count += countMap.getOrDefault(sum-target, 0);

            countMap.compute(sum, (k,v)->{
                if(null == v) v = 0;
                return v+1;
            });
        }
        return count;
    }
}
