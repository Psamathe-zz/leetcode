package com.example.leetcode.biweeklycontest.contest46;


import java.util.ArrayList;
import java.util.List;

/**
 * There is a tree (i.e., a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges. Each node has a value associated with it, and the root of the tree is node 0.
 *
 * To represent this tree, you are given an integer array nums and a 2D array edges. Each nums[i] represents the ith node's value, and each edges[j] = [uj, vj] represents an edge between nodes uj and vj in the tree.
 *
 * Two values x and y are coprime if gcd(x, y) == 1 where gcd(x, y) is the greatest common divisor of x and y.
 *
 * An ancestor of a node i is any other node on the shortest path from node i to the root. A node is not considered an ancestor of itself.
 *
 * Return an array ans of size n, where ans[i] is the closest ancestor to node i such that nums[i] and nums[ans[i]] are coprime, or -1 if there is no such ancestor.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: nums = [2,3,3,2], edges = [[0,1],[1,2],[1,3]]
 * Output: [-1,0,0,1]
 * Explanation: In the above figure, each node's value is in parentheses.
 * - Node 0 has no coprime ancestors.
 * - Node 1 has only one ancestor, node 0. Their values are coprime (gcd(2,3) == 1).
 * - Node 2 has two ancestors, nodes 1 and 0. Node 1's value is not coprime (gcd(3,3) == 3), but node 0's
 *   value is (gcd(2,3) == 1), so node 0 is the closest valid ancestor.
 * - Node 3 has two ancestors, nodes 1 and 0. It is coprime with node 1 (gcd(3,2) == 1), so node 1 is its
 *   closest valid ancestor.
 * Example 2:
 *
 *
 *
 * Input: nums = [5,6,10,2,3,6,15], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
 * Output: [-1,0,-1,0,0,0,-1]
 *
 *
 * Constraints:
 *
 * nums.length == n
 * 1 <= nums[i] <= 50
 * 1 <= n <= 105
 * edges.length == n - 1
 * edges[j].length == 2
 * 0 <= uj, vj < n
 * uj != vj
 */
public class TreeCoprimes {
    public static void main(String[] args) {

    }

    /**
     * 因本题节点取值范围较小，以此切入解题
     *
     * 维护节点队列 path 保存节点下标，队列中节点值不重复，按远近排列
     * （我就是不想维护深度！
     *
     * 该队列非常适合使用链表实现，回溯时也方便复原
     * （有空再写
     * https://leetcode-cn.com/problems/tree-of-coprimes/solution/wei-hu-zui-jin-de-zhi-dui-lie-by-ayaphis-dbd4/
     * @param nums
     * @param edges
     * @return
     */
    public int[] getCoprimes(int[] nums, int[][] edges) {
        List<Integer>[] nodes = new List[nums.length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            nodes[edge[0]].add(edge[1]);
            nodes[edge[1]].add(edge[0]);
        }
        List<Integer> nearest = new ArrayList<>();
        int[] ret = new int[nums.length];
        dfs(nodes, -1, 0, nearest ,ret, nums);
        return ret;
    }

    private void dfs(List<Integer>[] nodes, int from, int to, List<Integer> path, int[] ret, int[] nums) {
        List<Integer> node = nodes[to];
        int idx = -1;
        List<Integer> next_path = new ArrayList<>();
        for (int i : path) {
            if (nums[i] != nums[to]){
                next_path.add(i);
            }
            if (gcd(nums[i], nums[to]) == 1){
                idx = i;
            }
        }
        next_path.add(to);
        ret[to] = idx;
        for (int child : node){
            if (child == from) continue;
            dfs(nodes, to, child, next_path, ret, nums);
        }
    }
    private int gcd(int a, int b){
        if (a < b){
            return gcd(b, a);
        }
        while (b > 0){
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
