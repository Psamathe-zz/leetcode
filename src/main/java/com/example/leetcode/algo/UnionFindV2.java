package com.example.leetcode.algo;

/**
 * https://leetcode-cn.com/problems/find-all-people-with-secret/solution/bing-cha-ji-java-shuang-100-by-smqk-1que/
 */
public class UnionFindV2 {
    // father[2] = 3 表示元素2的父节点是3
    public int[] father;

    public UnionFindV2(int len) {
        father = new int[len];
        for (int i = 0; i < len; i++) {
            father[i] = i;
        }
    }

    // 从并查集中拆分出来
    public void split(int x){
        father[x] = x;
    }

    // 查询 x 的根节点
    public int find(int x) {
        if (x < 0 || x > father.length - 1) {
            throw new RuntimeException("查询越界");
        }

        // 合并（路径压缩）
        return (x == father[x] ? x : (father[x] = find(father[x])));
    }

    // 合并节点, y 的根节点指向 x 的根节点
    public void merge(int x, int y) {
        int xRoot = find(x), yRoot = find(y);
        father[yRoot] = xRoot;
    }
}
