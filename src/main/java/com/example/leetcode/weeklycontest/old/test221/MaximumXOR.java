package com.example.leetcode.weeklycontest.old.test221;

import java.util.Arrays;

/**
 * You are given an array nums consisting of non-negative integers. You are also given a queries array, where queries[i] = [xi, mi].
 *
 * The answer to the ith query is the maximum bitwise XOR value of xi and any element of nums that does not exceed mi. In other words, the answer is max(nums[j] XOR xi) for all j such that nums[j] <= mi. If all elements in nums are larger than mi, then the answer is -1.
 *
 * Return an integer array answer where answer.length == queries.length and answer[i] is the answer to the ith query.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
 * Output: [3,3,7]
 * Explanation:
 * 1) 0 and 1 are the only two integers not greater than 1. 0 XOR 3 = 3 and 1 XOR 3 = 2. The larger of the two is 3.
 * 2) 1 XOR 2 = 3.
 * 3) 5 XOR 2 = 7.
 * Example 2:
 *
 * Input: nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
 * Output: [15,-1,5]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length, queries.length <= 105
 * queries[i].length == 2
 * 0 <= nums[j], xi, mi <= 109
 */
public class MaximumXOR {
    public static void main(String[] args) {

    }

    int[][] son;
    int idx;
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        son = new int[n * 31][2];
        // 添加index
        Pair[] pair = new Pair[queries.length];
        for(int i = 0; i < queries.length; i++) {
            pair[i] = new Pair(queries[i][0], queries[i][1], i);
        }
        //把queries按照mi从小到大排序
        Arrays.sort(pair, (o1, o2) ->(o1.m - o2.m));
        int[] ans = new int[queries.length];
        int pos = 0;
        for(Pair q : pair) {
            //所有<=m的数字，可以加入当前字典树中
            while(pos < n && nums[pos] <= q.m) {
                insert(nums[pos]);
                pos++;
            }
            //写答案
            if(idx == 0) {
                ans[q.index] = -1;
            }else {
                int t = query(q.x);
                ans[q.index] = q.x ^ t;
            }
        }
        return ans;
    }
    //返回此时trie树中的数字里，与a异或最大的数字
    private int query(int a){
        int p = 0, res = 0;
        for(int i = 30; i >= 0; i--){
            int u = a >> i & 1;//最高位，次高位，...
            if(son[p][u ^ 1] != 0){//如果可以走，u是1，就往0走，u是0，就往1走
                res = res * 2 + u ^ 1;
                p = son[p][u ^ 1];
            }else{
                res = res *2 + u;
                p = son[p][u];
            }
        }
        return res;
    }
    //在trie树中插入数字a
    private void insert(int a){
        int p = 0;
        for(int i = 30; i >= 0; i--){
            int u = a >> i & 1;
            if(son[p][u] == 0){
                son[p][u] = ++idx;
            }
            p = son[p][u];
        }
    }
}
class Pair{
    int x;
    int m;
    int index;
    public Pair(int x, int m, int index) {
        this.x = x;
        this.m = m;
        this.index = index;
    }
}
