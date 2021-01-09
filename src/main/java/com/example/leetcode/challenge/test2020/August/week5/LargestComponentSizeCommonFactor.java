package com.example.leetcode.challenge.test2020.August.week5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a non-empty array of unique positive integers A, consider the following graph:
 *
 * There are A.length nodes, labelled A[0] to A[A.length - 1];
 * There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
 * Return the size of the largest connected component in the graph.
 *
 *
 *
 * Example 1:
 *
 * Input: [4,6,15,35]
 * Output: 4
 *
 * Example 2:
 *
 * Input: [20,50,9,63]
 * Output: 2
 *
 * Example 3:
 *
 * Input: [2,3,6,7,4,12,21,39]
 * Output: 8
 *
 * Note:
 *
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= 100000
 */
public class LargestComponentSizeCommonFactor {
    public static void main(String[] args) {
        int[] A = new int[]{4,6,15,35};
        LargestComponentSizeCommonFactor largestComponentSizeCommonFactor = new LargestComponentSizeCommonFactor();
        largestComponentSizeCommonFactor.largestComponentSize(A);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/13253468.html
     * @param A
     * @return
     */
    int[] root;
    public int largestComponentSize(int[] A) {

        int n = 0, mx = 0, res = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : A) mx = Math.max(mx, num);
        root = new int[mx + 1];
        for (int i = 1; i <= mx; ++i) root[i] = i;
        for (int num : A) {
            for (int d = (int)Math.sqrt(num); d >= 2; --d) {
                if (num % d == 0) {
                    root[find(num)] = root[find(d)];
                    root[find(num)] = root[find(num / d)];
                }
            }
        }
        for (int num : A) {
            int index = find(num);
            map.put(index,map.getOrDefault(index,0)+1);
            res = Math.max(res,map.get(index) );
        }
        return res;
    }

    int find( int x) {
        return root[x] == x ? x : (root[x] = find(root[x]));
    }


    /**
     * faster solution
     * https://leetcode.com/submissions/detail/388679893/?from=/explore/challenge/card/august-leetcoding-challenge/553/week-5-august-29th-august-31st/3442/
     */
    class UnionFind {
        int[] id;
        int[] rank;
        UnionFind(int n) {
            id = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                rank[i] = 1;
            }
        }

        int find(int p) {
            while (id[p] != p) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }

        void union(int p, int q) {
            p = find(p);
            q = find(q);
            if (p == q) {
                return;
            } else if (rank[p] < rank[q]) {
                id[q] = p;
                rank[p] += rank[q];
            } else {
                id[p] = q;
                rank[q] += rank[p];
            }
        }

        int getRank(int i) {
            return rank[find(i)];
        }
    }

    Set<Integer> getPrimeFactors(int val) {
        HashSet<Integer> set = new HashSet<>();

        while (val % 2 == 0) {
            set.add(2);
            val /= 2;
        }


        for (int i = 3; i * i <= val; i += 2) {
            while (val % i == 0) {
                set.add(i);
                val /= i;
            }
        }

        if (val > 1) {
            set.add(val);
        }
        return set;
    }
    public int largestComponentSizeV2(int[] A) {
        HashMap<Integer, Integer> first = new HashMap<>();
        UnionFind uf = new UnionFind(A.length);

        for (int i = 0; i < A.length; i++) {
            for (int p : getPrimeFactors(A[i])) {
                if (!first.containsKey(p)) {
                    first.put(p, i);
                } else {
                    uf.union(first.get(p), i);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < A.length; i++) {
            max = Math.max(uf.getRank(i), max);
        }

        return max;
    }
}
