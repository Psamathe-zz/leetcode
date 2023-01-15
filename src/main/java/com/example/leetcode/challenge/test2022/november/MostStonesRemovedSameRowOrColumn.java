package com.example.leetcode.challenge.test2022.november;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 *
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 *
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Explanation: One way to remove 5 stones is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,1].
 * 2. Remove stone [2,1] because it shares the same column as [0,1].
 * 3. Remove stone [1,2] because it shares the same row as [1,0].
 * 4. Remove stone [1,0] because it shares the same column as [0,0].
 * 5. Remove stone [0,1] because it shares the same row as [0,0].
 * Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
 * Example 2:
 *
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Explanation: One way to make 3 moves is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,0].
 * 2. Remove stone [2,0] because it shares the same column as [0,0].
 * 3. Remove stone [0,2] because it shares the same row as [0,0].
 * Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
 * Example 3:
 *
 * Input: stones = [[0,0]]
 * Output: 0
 * Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
 */
public class MostStonesRemovedSameRowOrColumn {
    public static void main(String[] args) {

    }

    /**
     * https://github.com/cherryljr/LeetCode/blob/master/Most%20Stones%20Removed%20with%20Same%20Row%20or%20Column.java
     * @param stones
     * @return
     */
    public int removeStones(int[][] stones) {
        UnionFind uf = new UnionFind(stones);
        for (int[] stone : stones) {
            // uf.union(stone[0], 10000 + stone[1]);
            // 因为index不存在负数，所以我们可以利用 负数 来表示 列坐标（该操作可以通过取反实现，使得程序运行更快）
            uf.union(stone[0], ~stone[1]);
        }

        Set<Integer> islands = new HashSet<>();
        // 计算图中存在的联通块个数
        for (int[] stone : stones) {
            islands.add(uf.find(stone[0]));
        }
        return stones.length - islands.size();
    }

    class UnionFind {
        Map<Integer, Integer> parent;

        UnionFind(int[][] stones) {
            parent = new HashMap<>();
            // Initialize the Union Find
            for (int[] stone : stones) {
                parent.put(stone[0], stone[0]);
                // parent.put(10000 + stone[1], 10000 + stone[1]);
                parent.put(~stone[1], ~stone[1]);
            }
        }

        public int find(int index) {
            if (index != parent.get(index)) {
                parent.put(index, find(parent.get(index)));
            }
            return parent.get(index);
        }

        public void union(int a, int b) {
            int aFather = find(a);
            int bFather = find(b);
            if (aFather != bFather) {
                parent.put(bFather, aFather);
            }
        }
    }
}
