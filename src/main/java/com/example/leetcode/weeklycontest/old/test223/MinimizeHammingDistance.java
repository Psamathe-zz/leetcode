package com.example.leetcode.weeklycontest.old.test223;


import java.util.HashMap;
import java.util.Map;

/**
 * You are given two integer arrays, source and target, both of length n. You are also given an array allowedSwaps where each allowedSwaps[i] = [ai, bi] indicates that you are allowed to swap the elements at index ai and index bi (0-indexed) of array source. Note that you can swap elements at a specific pair of indices multiple times and in any order.
 *
 * The Hamming distance of two arrays of the same length, source and target, is the number of positions where the elements are different. Formally, it is the number of indices i for 0 <= i <= n-1 where source[i] != target[i] (0-indexed).
 *
 * Return the minimum Hamming distance of source and target after performing any amount of swap operations on array source.
 *
 *
 *
 * Example 1:
 *
 * Input: source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
 * Output: 1
 * Explanation: source can be transformed the following way:
 * - Swap indices 0 and 1: source = [2,1,3,4]
 * - Swap indices 2 and 3: source = [2,1,4,3]
 * The Hamming distance of source and target is 1 as they differ in 1 position: index 3.
 * Example 2:
 *
 * Input: source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
 * Output: 2
 * Explanation: There are no allowed swaps.
 * The Hamming distance of source and target is 2 as they differ in 2 positions: index 1 and index 2.
 * Example 3:
 *
 * Input: source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps = [[0,4],[4,2],[1,3],[1,4]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * n == source.length == target.length
 * 1 <= n <= 105
 * 1 <= source[i], target[i] <= 105
 * 0 <= allowedSwaps.length <= 105
 * allowedSwaps[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 */
public class MinimizeHammingDistance {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/minimize-hamming-distance-after-swap-operations/solution/shuang-100-javaban-map-bing-cha-ji-by-li-n3op/
     * @param source
     * @param target
     * @param allowedSwaps
     * @return
     */
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;

        // 并查集记录联通关系
        UnionFind unionFind = new UnionFind(n);
        for (int[] allow : allowedSwaps) {
            unionFind.union(allow[0], allow[1]);
        }

        // 联通分支的根-->source中属于本联通分支的元素集合
        Map<Integer, CountedMap<Integer>> sMap = new HashMap<>();
        // 联通分支的根-->target中属于本联通分支的元素集合
        Map<Integer, CountedMap<Integer>> tMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = unionFind.find(i);
            sMap.computeIfAbsent(root, k->new CountedMap<>())
                    .increment(source[i]);
            tMap.computeIfAbsent(root, k->new CountedMap<>())
                    .increment(target[i]);
        }

        int ans = 0;
        // 比较不同，累加答案
        for (int i = 0; i < n; i++) {
            // 非连通分支的根元素，跳过
            if(unionFind.find(i) != i)continue;

            CountedMap<Integer> s = sMap.get(i);
            CountedMap<Integer> t = tMap.get(i);

            // 比较两个集合s和t的不同元素个数
            for (Map.Entry<Integer, Integer> entry : s.entrySet()) {
                Integer key = entry.getKey();
                Integer count = entry.getValue();
                Integer countT = t.get(key);

                if(countT == null) {
                    // s中存在，而t中不存在
                    ans += count;
                } else {
                    // s中比t中多出来的个数，也是答案
                    if(count > countT) {
                        ans += count - countT;
                    }
                    t.remove(key);
                }
            }
        }
        return ans;
    }

    // UnionFind.java
    public class UnionFind {
        int[] roots;

        public UnionFind(int size) {
            this.roots = new int[size];
            for (int i = 0; i < size; i++) {
                roots[i] = i;
            }
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }

        public int find(int index) {
            int i = index;
            while (i != roots[i]) {
                i = roots[i];
            }
            return roots[index] = i;
        }

        public void union(int x, int y) {
            roots[find(x)] = find(y);
        }
    }
    // CountedMap.java
    public class CountedMap<T> extends HashMap<T, Integer> {
        public void increment(T key) {
            put(key, getOrDefault(key, 0)+1);
        }
    }
}
