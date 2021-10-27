package com.example.leetcode.algo;

public class UnionFind {
    int[] parent;
    int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        int cur = x;
        while (parent[cur] != cur) {
            parent[cur] = parent[parent[cur]];
            cur = parent[cur];
        }
        parent[x] = cur;
        return cur;
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public void union(int x, int y) {
        //if (!gcd(x, y)) return;

        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;
        if (rank[rootX] < rank[rootY]) {
            union(y, x);
        } else {
            parent[rootY] = rootX;
            rank[rootX] += rank[rootY];
        }
    }

    public boolean gcd(int i, int j) {
        if (i < j) return gcd(j, i);

        while (i % j != 0) {
            int temp = j;
            j = i % j;
            i = temp;
        }
        return j > 1;
    }
}
