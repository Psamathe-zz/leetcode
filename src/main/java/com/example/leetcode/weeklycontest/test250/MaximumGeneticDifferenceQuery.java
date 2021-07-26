package com.example.leetcode.weeklycontest.test250;

import java.util.ArrayList;

/**
 * There is a rooted tree consisting of n nodes numbered 0 to n - 1. Each node's number denotes its unique genetic value (i.e. the genetic value of node x is x). The genetic difference between two genetic values is defined as the bitwise-XOR of their values. You are given the integer array parents, where parents[i] is the parent for node i. If node x is the root of the tree, then parents[x] == -1.
 *
 * You are also given the array queries where queries[i] = [nodei, vali]. For each query i, find the maximum genetic difference between vali and pi, where pi is the genetic value of any node that is on the path between nodei and the root (including nodei and the root). More formally, you want to maximize vali XOR pi.
 *
 * Return an array ans where ans[i] is the answer to the ith query.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: parents = [-1,0,1,1], queries = [[0,2],[3,2],[2,5]]
 * Output: [2,3,7]
 * Explanation: The queries are processed as follows:
 * - [0,2]: The node with the maximum genetic difference is 0, with a difference of 2 XOR 0 = 2.
 * - [3,2]: The node with the maximum genetic difference is 1, with a difference of 2 XOR 1 = 3.
 * - [2,5]: The node with the maximum genetic difference is 2, with a difference of 5 XOR 2 = 7.
 * Example 2:
 *
 *
 * Input: parents = [3,7,-1,2,0,7,0,2], queries = [[4,6],[1,15],[0,5]]
 * Output: [6,14,7]
 * Explanation: The queries are processed as follows:
 * - [4,6]: The node with the maximum genetic difference is 0, with a difference of 6 XOR 0 = 6.
 * - [1,15]: The node with the maximum genetic difference is 1, with a difference of 15 XOR 1 = 14.
 * - [0,5]: The node with the maximum genetic difference is 2, with a difference of 5 XOR 2 = 7.
 */
public class MaximumGeneticDifferenceQuery {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/maximum-genetic-difference-query/solution/li-yong-dfste-xing-mei-ci-du-shi-chu-li-0ddep/
     */
    // 树
    public ArrayList<Integer>[] tree;
    // 每个节点的query列表
    public ArrayList<Node>[] map;
    // 答案
    public int[] ans;
    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        int n = parents.length;
        tree = new ArrayList[n];
        map = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            map[i] = new ArrayList<>();
        }
        // 根
        int root = 0;
        // 建树
        for (int i = 0; i < n; i++) {
            if(parents[i] == -1) root = i;
            else tree[parents[i]].add(i);
        }
        int m = queries.length;
        // 封装query
        for (int i = 0; i < m; i++) {
            map[queries[i][0]].add(new Node(queries[i][1] , i));
        }
        ans = new int[m];
        Trie trie = new Trie();
        build(root , trie);
        return ans;
    }

    public void build(int root , Trie trie) {
        // 该链开始，字典树加入root
        trie.update(root , 1);
        // 查询当前节点
        int size = map[root].size();
        for (int i = 0; i < size; i++) {
            ans[map[root].get(i).ind] = trie.query(map[root].get(i).val);
        }
        // 继续该链的下一个节点
        size = tree[root].size();
        for (int i = 0; i < size; i++) {
            build(tree[root].get(i) , trie);
        }
        // 该链结束 ， 字典树删除root
        trie.update(root , -1);
    }

    // query封装
    class Node{
        public int val;
        public int ind;
        public Node(int val, int ind) {
            this.val = val;
            this.ind = ind;
        }
    }

    // 字典树
    class Trie {
        public int count = 0;
        public Trie[] tries = new Trie[2];
        public final int BITS_LIMIT = 20;

        public void update(int val , int num){
            Trie node = this;
            node.count += num;
            for (int i = BITS_LIMIT; i >= 0; i--) {
                int bits = (val >> i) & 1;
                if(node.tries[bits] == null){
                    node.tries[bits] = new Trie();
                }
                node = node.tries[bits];
                node.count += num;
            }
        }

        public int query(int val){
            Trie node = this;
            int ans = 0;
            for (int i = BITS_LIMIT; i >= 0; i--) {
                int bits = (val >> i) & 1;
                if(node.tries[bits ^ 1] != null && node.tries[bits ^ 1].count != 0){
                    ans |= (1 << i);
                    bits ^= 1;
                }
                node = node.tries[bits];
            }
            return ans;
        }
    }

}
