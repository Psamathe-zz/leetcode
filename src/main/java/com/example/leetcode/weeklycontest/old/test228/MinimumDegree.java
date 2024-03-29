package com.example.leetcode.weeklycontest.old.test228;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumDegree {

    public static void main(String[] args) {

    }

    /**
     *
     作者：mufanlee
     链接：https://leetcode-cn.com/problems/minimum-degree-of-a-connected-trio-in-a-graph/solution/5679-yi-ge-tu-zhong-lian-tong-san-yuan-z-n5u7/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @param edges
     * @return
     */
    public int minTrioDegree(int n, int[][] edges) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new HashSet<>());
        }
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        int ans = edges.length * 2;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                for (int k = j + 1; k <= n; k++) {
                    if (isTriple(i, j, k, map)) {
                        ans = Math.min(ans, map.get(i).size() + map.get(j).size() + map.get(k).size() - 6);
                    }
                }
            }
        }
        return ans == edges.length * 2 ? -1 : ans;
    }

    private boolean isTriple(int i, int j, int k, Map<Integer, Set<Integer>> map) {
        return map.get(i).contains(j) && map.get(i).contains(k) && map.get(j).contains(k);
    }
}
