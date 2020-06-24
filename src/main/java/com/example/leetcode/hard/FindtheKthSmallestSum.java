package com.example.leetcode.hard;

import java.util.*;

/**
 * You are given an m * n matrix, mat, and an integer k, which has its rows sorted in non-decreasing order.
 *
 * You are allowed to choose exactly 1 element from each row to form an array. Return the Kth smallest array sum among all possible arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,3,11],[2,4,6]], k = 5
 * Output: 7
 * Explanation: Choosing one element from each row, the first k smallest sum are:
 * [1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.
 * Example 2:
 *
 * Input: mat = [[1,3,11],[2,4,6]], k = 9
 * Output: 17
 * Example 3:
 *
 * Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
 * Output: 9
 * Explanation: Choosing one element from each row, the first k smallest sum are:
 * [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.
 * Example 4:
 *
 * Input: mat = [[1,1,10],[2,2,9]], k = 7
 * Output: 12
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat.length[i]
 * 1 <= m, n <= 40
 * 1 <= k <= min(200, n ^ m)
 * 1 <= mat[i][j] <= 5000
 * mat[i] is a non decreasing array.
 */
public class FindtheKthSmallestSum {
    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1,3,11},
                {2,4,6}
        };
        int k = 9;
        FindtheKthSmallestSum findtheKthSmallestSum = new FindtheKthSmallestSum();
        int result = findtheKthSmallestSum.kthSmallest(mat,k);
        System.out.println(result);
    }
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        int[] node = new int[m + 1]; // {-sum, idx_0, idx_1, ..., idx_m}
        for (int i = 0; i < m; ++i)
            node[0] -= mat[i][0];
        queue.add(node);
        Set<Node> seen = new HashSet<>();
        seen.add(new Node(node));
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (--k == 0)
                return -cur[0];
            for (int i = 1; i <= m; ++i) {
                if (cur[i] == n - 1)
                    continue;
                int[]  nxt = cur.clone();
                ++nxt[i]; // increase i-th row's index.
                // Update sum.
                nxt[0] -= (mat[i - 1][nxt[i]] - mat[i - 1][nxt[i] - 1]);
                if (seen.contains(new Node(nxt))){
                    continue;
                }
                seen.add(new Node(nxt));
                queue.add(nxt);
            }
        }
        return -1;
    }

    public class Node{
        int[] cur;

        public Node(int[] cur) {
            this.cur = cur;
        }

        public int[] getCur() {
            return cur;
        }

        public void setCur(int[] cur) {
            this.cur = cur;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Arrays.equals(cur, node.cur);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(cur);
        }
    }


    /**
     * faster solution
     * @param mat
     * @param k
     * @return
     */
    public int kthSmallestV1(int[][] mat, int k) {
        if (mat.length == 1) return mat[0][k-1];
        if (mat[0].length == 1) return mat[k-1][0];
        int[] ret = mat[0];
        for (int i = 1; i < mat.length; i++) {
            ret = mergeArr(mat[i], ret, k);
        }
        return ret[k - 1];
    }
    int[] mergeArr(int[] arr1, int[] arr2, int k) {
        k = Math.min(k, arr1.length * arr2.length);
        int[] index = new int[arr1.length];
        int n = 0;
        int m = 0;
        int[] ret = new int[k];
        int s = 0;
        while (s < k) {
            int tmp = Integer.MAX_VALUE;
            int j = -1;
            for (int i = m; i <= n; i++) {
                if (tmp > arr1[i] + arr2[index[i]]) {
                    tmp = arr1[i] + arr2[index[i]];
                    j = i;
                }
            }
            ret[s++] = tmp;
            if (index[j] == 0 && n < arr1.length - 1) n++;
            if (index[j] < arr2.length - 1) {
                index[j]++;
            } else {
                m++;
            }
        }
        return ret;
    }


    /**
     * fater v2
     * @param mat
     * @param k
     * @return
     */
    public int kthSmallestV2(int[][] mat, int k) {
        return binarySearch(mat, k);
        //return bfs(mat, k);
    }
    int count = 0;
    //klogk
    public int binarySearch(int[][] mat, int k) {
        int lo = 0, hi = 0;
        int M = mat.length, N = mat[0].length;
        for(int[] R: mat){
            lo += R[0];
            hi += R[N - 1];
        }
        int base = lo;
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            count = 1;//initial when we choose all the first 0 element
            count(mat, 0, base, mid, k, M, N);
            if(count < k){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
    //count how many pair <= mid
    public void count(int[][] mat, int row, int sum , int mid, int k, int M, int N) {
        if(row == M//out of bound
                ||
                count > k// pruning //making it O(k)
        ) return;
        count(mat, row + 1, sum, mid, k, M, N); //choose the first
        //choosing the next col to replace the first
        for(int c = 1; c < N; c++) {
            if(sum - mat[row][0] + mat[row][c] > mid) {
                break;
            }
            count++;
            count(mat, row + 1, sum - mat[row][0] + mat[row][c], mid, k, M, N);
        }
    }
    //k log(M)
    public int bfs(int[][] mat, int k) {

        int M = mat.length;
        int N = Math.min(k, mat[0].length);

        Entry initial = new Entry(new int[M], 0);
        for(int[] R: mat){ initial.sum += R[0]; }

        PriorityQueue<Entry> Q = new PriorityQueue<Entry>((a, b) -> {
            return Integer.compare(a.sum, b.sum);
        });
        Set<String> visited = new HashSet<>();

        Q.offer(initial);
        visited.add(initial.toString());
        Entry curr, next;
        //k log(M)
        while(!Q.isEmpty()) {
            --k;
            curr = Q.poll();
            //System.out.println(curr.toString());
            if(k == 0) return curr.sum;
            for(int i = 0; i < mat.length; i++) {
                next = curr.clone();
                if(next.indices[i] + 1 == N) continue;
                next.sum -= mat[i][next.indices[i]];
                next.sum += mat[i][++next.indices[i]];
                if(visited.add(next.toString())){
                    Q.offer(next);
                }
            }

        }
        return -1;
    }
    class Entry {
        int[] indices;
        int sum;

        public Entry(int[] idx, int s) {
            this.indices = idx;
            this.sum = s;
        }

        public String toString() {
            return Arrays.toString(indices);// + " " + sum;
        }

        public Entry clone() {
            return new Entry(Arrays.copyOf(this.indices, this.indices.length), this.sum);
        }
    }

        public int kthSmallestOld(int[][] mat, int k) {
            int u = mat.length;
            int v = mat[0].length;
            int[] index = new int[u];
            int currentValue = 0;
            int min;
            int minIndex;
            for (int j = 0; j < u; j++) {
                currentValue += mat[j][index[j]];
            }

            for (int i = 1; i < k; i++) {
                min = currentValue + mat[0][index[0] + 1] - mat[0][index[0]];
                minIndex = 0;
                for (int j = 1; j < u; j++) {
                    if (index[j] == v - 1) {
                        continue;
                    }
                    if (min > currentValue + mat[j][index[j] + 1] - mat[j][index[j]]) {
                        min = currentValue + mat[j][index[j] + 1] - mat[j][index[j]];
                        minIndex = j;
                    }
                }
                currentValue = min;
                index[minIndex]++;
            }
            return currentValue;
        }
}
