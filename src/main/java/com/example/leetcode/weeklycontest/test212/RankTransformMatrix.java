package com.example.leetcode.weeklycontest.test212;


import java.util.*;

/**
 * Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].
 *
 * The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:
 *
 * If an element is the smallest element in its row and column, then its rank is 1.
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
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 500
 * -109 <= matrix[row][col] <= 109
 */
public class RankTransformMatrix {
    public static void main(String[] args) {
        RankTransformMatrix rankTransform = new RankTransformMatrix();
        rankTransform.matrixRankTransform(new int[][]{
                {-37,-50,-3,44},
                {-37,46,13,-32},
                {47,-42,-3,-40},
                {-17,-22,-39,24}});
    }


    //朴实无华不带rank的并查集实现
    class DJU{
        int rank;
        DJU p;
        DJU(int rank){
            this.rank = rank;
            p = this;
        }

        DJU find() {
            if(p.p != p) {
                p = p.find();
            }
            return p;
        }

        void union(DJU another) {
            this.find().p = another.find();
        }
    }

    /**
     * https://leetcode-cn.com/problems/rank-transform-of-a-matrix/solution/you-xian-dui-lie-ha-xi-biao-bing-cha-ji-by-cuikai1/
     * @param matrix
     * @return
     */
    public int[][] matrixRankTransform(int[][] matrix) {
        //今天是优先队列专场?
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] ret = new int[m][n];
        int[] rowNext = new int[m];//每一行目前最大rank的数
        int[] colNext = new int[n];//每一列目前最大数

        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b)->matrix[a[0]][a[1]] - matrix[b[0]][b[1]]);
        for(int i = 0; i < m; ++i)
            for(int j = 0; j < n; ++j) {
                queue.add(new int[] {i, j});
            }

        while (!queue.isEmpty()) {
            //equals里头有若干的点 把它们合并成若干并查集 每一个并查集中的rank相同 合并完毕后统一更新
            //提取若干个数值相同的位置
            int num = matrix[queue.peek()[0]][queue.peek()[1]];
            List<int[]> equals = new ArrayList<int[]>();
            while(!queue.isEmpty() && matrix[queue.peek()[0]][queue.peek()[1]] == num) {
                equals.add(queue.poll());
            }

            //计算每一个的rank 创建对应并查集 并查集中的rank不是并查集的rank而是题目的rank...
            DJU[] set = new DJU[equals.size()];
            for(int i = 0; i < equals.size(); ++i) {
                int[] p = equals.get(i);
                int r = Math.max(rowNext[p[0]], colNext[p[1]]) + 1;
                set[i] = new DJU(r);
            }

            //用哈希表(数组哈希表一回事了) 存储当前行列的并查集
            DJU[] Rows = new DJU[m];
            DJU[] Cols = new DJU[n];
            for(int i = 0; i < equals.size(); ++i) {
                int[] p = equals.get(i);
                if(Rows[p[0]] == null && Cols[p[1]] == null) {
                    Rows[p[0]] = set[i];
                    Cols[p[1]] = set[i];
                } else if(Cols[p[1]] == null) {
                    Cols[p[1]] = set[i];
                    Rows[p[0]].union(Cols[p[1]]);
                } else if(Rows[p[0]] == null) {
                    Rows[p[0]] = set[i];
                    Rows[p[0]].union(Cols[p[1]]);
                } else {
                    Rows[p[0]].union(set[i]);
                    Rows[p[0]].union(Cols[p[1]]);
                }
            }

            //将每一个并查集的rank置为该并查集中最大的rank
            for(int i = 0; i < equals.size(); ++i) {
                if(set[i].rank > set[i].find().rank) {
                    set[i].find().rank = set[i].rank;//每一个集合都存储最大的可行rank
                }
            }

            //更新ret中rank 更新呗更新位置所在行列的当前rank
            for(int i = 0; i < equals.size(); ++i) {
                int[] p = equals.get(i);
                rowNext[p[0]] = set[i].find().rank;
                colNext[p[1]] = set[i].find().rank;
                ret[p[0]][p[1]] = set[i].find().rank;
            }
        }
        return ret;
    }


    /**
     * https://leetcode-cn.com/problems/rank-transform-of-a-matrix/solution/javadai-ma-de-pai-xu-bing-cha-ji-tong-su-yi-dong-b/
     * @param matrix
     * @return
     */
    public int[][] matrixRankTransformV1(int[][] matrix) {
        final int row = matrix.length;
        final int col = matrix[0].length;

        Integer[] indexs = new Integer[row * col];
        for (int i = 0; i < indexs.length; i++) {
            indexs[i] = i;
        }

        Arrays.sort(indexs, new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                return matrix[t1 / col][t1 % col] - matrix[t2 / col][t2 % col];
            }
        });

        int[] minRows = new int[row];
        int[] minCols = new int[col];
        Arrays.fill(minRows, -1);
        Arrays.fill(minCols, -1);
        int[] leaders = new int[row * col];
        int[] leaderVals = new int[row * col];
        for (int i = 0; i < leaders.length; i++) {
            leaders[i] = i;
        }

//        int[][] ans = new int[row][col];
        int pos = 0;
        while (pos < indexs.length) {

            int val = 1;
            int index = indexs[pos];

            int i = index / col;
            int j = index % col;
            int tmpIndex;
            int tmpVal;
            if (minRows[i] != -1) {
                tmpIndex = i * col + minRows[i];
                int leaderIndex = getLeader(leaders, tmpIndex);
                tmpVal = leaderVals[leaderIndex];
                if (matrix[i][j] == matrix[i][minRows[i]]) {
                    mergeLeader(leaders, index, tmpIndex);
                    val = Math.max(val, tmpVal);
                } else {
                    val = Math.max(val, tmpVal + 1);
                }
            }
            if (minCols[j] != -1) {
                tmpIndex = minCols[j] * col + j;
                int leaderIndex = getLeader(leaders, tmpIndex);
                tmpVal = leaderVals[leaderIndex];
                if (matrix[i][j] == matrix[minCols[j]][j]) {
                    mergeLeader(leaders, index, tmpIndex);
                    val = Math.max(val, tmpVal);
                } else {
                    val = Math.max(val, tmpVal + 1);
                }
            }

            minRows[i] = j;
            minCols[j] = i;

            int leader = getLeader(leaders, index);
            leaderVals[leader] = val;
            pos++;

        }
        int[][] ans = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int index = i * col + j;
                ans[i][j] = leaderVals[getLeader(leaders, index)];
            }
        }
        return ans;
    }

    private void mergeLeader(int[] leaders, int index, int tmpIndex) {
        int leader1 = getLeader(leaders, index);
        int leader2 = getLeader(leaders, tmpIndex);
        if (leader1 != leader2) {
            leaders[leader1] = leader2;
        }
    }

    private int getLeader(int[] leaders, int tmpIndex) {
        int leader = leaders[tmpIndex];
        if (leader == leaders[leader]) {
            return leader;
        } else {
            return leaders[tmpIndex] = getLeader(leaders, leader);
        }
    }

}
